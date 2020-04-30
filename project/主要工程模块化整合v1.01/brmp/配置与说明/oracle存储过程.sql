/************************************************
*
*  创建计算uuid
*
*********************************************/

create or replace and compile java source named my_uuid as
import java.io.UnsupportedEncodingException;
import java.util.UUID;

public class MyUUID {

  public static String getUUID(String idSrouce) throws UnsupportedEncodingException{
    byte[] idBSrouce = idSrouce.getBytes("UTF-8");
    return UUID.nameUUIDFromBytes(idBSrouce).toString().replace("-", "");
  }

}


/************************************************
*
*  创建packge
*
*********************************************/

CREATE OR REPLACE PACKAGE S_PKG_EXEBRMPJOB as
       /*将主键拼接成一个字符串,计算一个唯一id返回*/
       Function GET_MY_UUID(id_srouce in varchar2) return varchar2;
       
       /*资源管理作业前置工作*/
       PROCEDURE P_CEN_BRMP_BEGIN;
       /*资源管理更新对面网络传入来的数据去重进入主题库*/
       PROCEDURE P_CEN_BRMP_CHANGE;
       /*资源管理将临时表的数据去重传入主题库*/
       PROCEDURE P_CEN_BRMP_TEMP;
       /*资源管理作业后置工作*/
       PROCEDURE P_CEN_BRMP_AFTER;
       
       
END S_PKG_EXEBRMPJOB;

/************************************************
*
*  创建packge body
*
*********************************************/
CREATE OR REPLACE PACKAGE BODY S_PKG_EXEBRMPJOB as
       function get_my_UUID(id_srouce in varchar2) return varchar2 as language java name 'MyUUID.getUUID(java.lang.String) return java.lang.String';
       
       PROCEDURE P_CEN_BRMP_BEGIN is
         col_sql      Varchar2(4000) := ''; --动态sql保存
         --script       Clob;
         --pkno         int := 0; --pk字段循环数
         begin
           
         For table_list In ( select m.model_name as table_cname,
                                      m.model_tab_name as TABLE_NAME,
                                      (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id) as col,
                                      (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id and md.pk=1 ) as pk
                               from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                               where m.status = 1 and m.audit_status = 9 )
         Loop
         BEGIN
               /*
               * 整理临时表重建临时表索引
               */
               col_sql := 'analyze table cen_brmp.' || table_list.TABLE_NAME || '_TEMP compute statistics' ;
               execute immediate col_sql;

                FOR STR3 IN (   select 'CEN_BRMP' owner,t.INDEX_NAME
                                from user_indexes t
                                where t.table_name = table_list.table_name||'_TEMP'
                                and t.index_name not like '%SYS\_IL0000%' escape '\'
                ) LOOP
                col_sql := 'ALTER INDEX ' || STR3.OWNER || '.' || STR3.INDEX_NAME || ' REBUILD' ;
                /*
                if table_list.table_name not in ('TB_XXZX_JCJYBG','YZ_YYHCCGXX','YZ_YYHCMLXX') then
                   EXECUTE IMMEDIATE col_sql;
                END if;
                */
                EXECUTE IMMEDIATE col_sql;
                END LOOP;  
                
               /*
               * 整理对网交换表重建对网交换表索引
               */
               col_sql := 'analyze table cen_brmp.' || table_list.TABLE_NAME || '_CHANGE compute statistics' ;
               execute immediate col_sql;

                FOR STR4 IN (   select 'CEN_BRMP' owner,t.INDEX_NAME
                                from user_indexes t
                                where t.table_name = table_list.table_name||'_CHANGE'
                                and t.index_name not like '%SYS\_IL0000%' escape '\'
                ) LOOP
                col_sql := 'ALTER INDEX ' || STR4.OWNER || '.' || STR4.INDEX_NAME || ' REBUILD' ;
                EXECUTE IMMEDIATE col_sql;
                END LOOP;
                
                /*
                 * 将临时表作业标志 修改为 1 进行处理
                 */
                 col_sql := 'update cen_brmp.'|| table_list.table_name || '_TEMP set ZYBZ= 1 where ZYBZ= 0';
                 --dbms_output.put_line(col_sql);
                 execute immediate col_sql;
                 commit;
                 
                 /*
                 * 将对网交换表作业标志 修改为 1 进行处理
                 */
                 col_sql := 'update cen_brmp.'|| table_list.table_name || '_CHANGE set ZYBZ= 1 where ZYBZ= 0';
                 --dbms_output.put_line(col_sql);
                 execute immediate col_sql;
                 commit; 
                
                /*
                * 设置临时表插入的数据的id
                * 替换临时表id值，用主键拼接计算唯一id
                */
                col_sql := 'update cen_brmp.' || table_list.TABLE_NAME ||'_TEMP set id= S_PKG_EXEBRMPJOB.GET_MY_UUID(' || replace(table_list.pk,',','||') || ') where ZYBZ= 1 ';
                --dbms_output.put_line(col_sql);
                execute immediate col_sql;
                commit;
                
                /*
                * 替换临时表日期为 1900-01-01 为null
                * java保存空值会报错，先替换了一个非空日期存入数据库
                * 
                */
                for date_cols in ( 
                              select t.model_col_name from cen_brmp.brmp_conf_origin_system_model t 
                              where t.model_id = (select model_id from cen_brmp.brmp_conf_origin_system_mdbase a where a.model_tab_name=table_list.table_name)
                              and t.model_col_type = 3 
                              )
                loop
                    col_sql := 'update cen_brmp.' || table_list.TABLE_NAME ||'_TEMP set ' || date_cols.model_col_name || ' = null where ZYBZ=1 and ' || date_cols.model_col_name || ' = to_date(''19000101'',''yyyymmdd'')';
                    --dbms_output.put_line(col_sql);
                    execute immediate col_sql;
                    commit;
                end loop;  
                
                /*
                * 替换对网交换表日期为 1900-01-01 为null
                * java保存空值会报错，先替换了一个非空日期存入数据库
                *
                */
                for date_cols in ( 
                              select t.model_col_name from cen_brmp.brmp_conf_origin_system_model t 
                              where t.model_id = (select model_id from cen_brmp.brmp_conf_origin_system_mdbase a where a.model_tab_name=table_list.table_name)
                              and t.model_col_type = 3 
                              )
                loop
                    col_sql := 'update cen_brmp.' || table_list.TABLE_NAME ||'_CHANGE set ' || date_cols.model_col_name || ' = null where ZYBZ=1 and ' || date_cols.model_col_name || ' = to_date(''19000101'',''yyyymmdd'')';
                    --dbms_output.put_line(col_sql);
                    execute immediate col_sql;
                    commit;
                end loop;
                
                
                  
         Exception
                 When Others Then
                 Rollback;
                 DBMS_OUTPUT.ENABLE(buffer_size => null);
                 dbms_output.put_line('P_CEN_BRMP_BEGIN出错！'|| table_list.table_name || table_list.table_cname  || ':->' || Sqlcode || '->' || Sqlerrm);
               
         END;
         END loop;  
           
       END P_CEN_BRMP_BEGIN;

       PROCEDURE P_CEN_BRMP_CHANGE is
          col_sql      Varchar2(4000) := ''; --动态sql保存
          --cols_str     Varchar2(4000) := ''; --字段名称拼装
          --i_total      Number := 0; --插入成功后影响的数据条数
          --v_msg        Varchar2(1000); --错误信息
          script       Clob;
          pkno         int := 0; --pk字段循环数

          --vext_columns Varchar2(4000);
          --vprc_name    Varchar2(100);

          begin
          --vprc_name := 'P_CEN_BRMP_CHANGE';

          For table_list In ( select m.model_name as table_cname,
                                m.model_tab_name as TABLE_NAME,
                                (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id) as col,
                                (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id and md.pk=1 ) as pk
                         from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                         where m.status = 1 and m.audit_status = 9 )
          Loop
          begin



           /**
           * 更新ex表，在temp表有新数据的记录 向其它接口发送数据的成功标志重新置为0
           * cgbz_1本来就是对面同步过来的数据，不用再发送给对面，此字段不用再重置
           *
           col_sql := 'update cen_brmp.' || table_list.table_name || '_ex ex set cgbz_1=0 where ex.rowid in ( select a.rowid from cen_brmp.' || table_list.table_name || '_ex a inner join (select ' || table_list.pk || ' from cen_brmp.' || table_list.table_name || '_CHANGE b where b.zybz=1 group by ' || table_list.pk ||' ) c ';

           pkno := 0;
           For pks in ( select md.model_col_name pk
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                      where md.pk=1
                      and md.model_id = (
                      select m.model_id
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                      where m.status = 1 and m.audit_status = 9
                      and m.model_tab_name = table_list.table_name)
                      )
            loop
              if pkno = 0 then
                 col_sql := col_sql || 'on a.'|| pks.pk  || ' = c.'|| pks.pk ;
              else
                 col_sql := col_sql || 'and a.'|| pks.pk  || ' = c.'|| pks.pk ;
              end if;
              pkno := pkno+1;
            end loop;
            col_sql := col_sql ||')' ;
           --dbms_output.put_line(col_sql);
           execute immediate col_sql;
           */

           /*
           * 删除在主题库的重复数据(与临时库比较得出)
           *
           *  --可能需要添加 table_list.pk ，根据pk字段，自表去重
           *  'delete from '|| table_list.table_name || ' where rowid not in ( select max(rowid) from ' || table_list.table_name || ' group by ' || table_list.pk || ') '
           */

           col_sql := 'delete from cen_brmp.' || table_list.table_name || ' a1 where a1.rowid in ( select a.rowid from cen_brmp.' || table_list.table_name || ' a inner join (select ' || table_list.pk || ' from cen_brmp.' || table_list.table_name || '_CHANGE b where b.zybz=1 group by ' || table_list.pk ||' ) c ';

           pkno := 0;
           For pks in ( select md.model_col_name pk
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                      where md.pk=1
                      and md.model_id = (
                      select m.model_id
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                      where m.status = 1 and m.audit_status = 9
                      and m.model_tab_name = table_list.table_name)
                      )
            loop
              if pkno = 0 then
                 col_sql := col_sql || 'on a.'|| pks.pk  || ' = c.'|| pks.pk ;
              else
                 col_sql := col_sql || 'and a.'|| pks.pk  || ' = c.'|| pks.pk ;
              end if;
              pkno := pkno+1;
            end loop;
            col_sql := col_sql ||')' ;
           --dbms_output.put_line(col_sql);
           execute immediate col_sql;


           /*
           * 临时表去重插入主题库
           */
           script := 'insert into cen_brmp.' || table_list.table_name || '(' || table_list.col || ')'
                  || 'select ' || table_list.col || ' from ' || table_list.table_name
                  || '_CHANGE a where a.ZYBZ=1 and a.rowid = (select max(rowid) from cen_brmp.'  || table_list.table_name
                  || '_CHANGE b where b.ZYBZ=1 ' ;

           For pks in ( select md.model_col_name pk
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                      where md.pk=1
                      and md.model_id = (
                      select m.model_id
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                      where m.status = 1 and m.audit_status = 9
                      and m.model_tab_name = table_list.table_name)
                      )
            loop
              script := script || 'and a.'|| pks.pk  || ' = b.'|| pks.pk ;
            end loop;
            script := script ||')' ;

           --DBMS_OUTPUT.ENABLE(buffer_size => null);
           --dbms_output.put_line(script);
           execute immediate script;


           /*
           * 临时表去重插入ex扩展信息表。
           * cgbz_1本来就是对面网络同步过来的数据，不用再发送给对面，此字段不用再默认为0
           */
           script := 'insert into cen_brmp.' || table_list.table_name || '_ex (' || table_list.pk || ',cgbz_1 )'
                  || 'select ' || table_list.pk || ',''1'' from ' || table_list.table_name
                  || '_CHANGE a where a.ZYBZ=1 and a.rowid = (select max(rowid) from cen_brmp.'  || table_list.table_name
                  || '_CHANGE b where b.ZYBZ=1 ' ;

           For pks in ( select md.model_col_name pk
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                      where md.pk=1
                      and md.model_id = (
                      select m.model_id
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                      where m.status = 1 and m.audit_status = 9
                      and m.model_tab_name = table_list.table_name)
                      )
            loop
              script := script || 'and a.'|| pks.pk  || ' = b.'|| pks.pk ;
            end loop;
            script := script ||' ) and a.rowid not in ( select a1.rowid from cen_brmp.'|| table_list.table_name ||'_CHANGE a1 inner join (select '
                   || table_list.pk || ' from ' || table_list.table_name || '_ex b1 group by ' || table_list.pk || ' ) c1 ';

            pkno := 0;
            For pks in ( select md.model_col_name pk
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                      where md.pk=1
                      and md.model_id = (
                      select m.model_id
                      from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                      where m.status = 1 and m.audit_status = 9
                      and m.model_tab_name = table_list.table_name)
                      )
            loop
              if pkno = 0 then
                 script := script || ' on a1.'|| pks.pk  || ' = c1.'|| pks.pk ;
              else
                 script := script || ' and a1.'|| pks.pk  || ' = c1.'|| pks.pk ;
              end if;
                 pkno := pkno+1;
            end loop;
            script := script ||' ) ';

            --DBMS_OUTPUT.ENABLE(buffer_size => null);
            --dbms_output.put_line(script);
            execute immediate script;


           --将插入成功的作业标志修改为 2
           col_sql := 'update cen_brmp.'|| table_list.table_name || '_CHANGE set ZYBZ= 2 where ZYBZ=1';
          -- dbms_output.put_line(col_sql);
           execute immediate col_sql;
           commit;


           /*
           * 删除临时表中，已经成功插入主题库的数据，通过作业标志为 2
           */
           col_sql := 'delete cen_brmp.'|| table_list.table_name || '_CHANGE where ZYBZ= 2';
          -- dbms_output.put_line(col_sql);
          execute immediate col_sql;
          commit;

          Exception
           When Others Then
           Rollback;
           DBMS_OUTPUT.ENABLE(buffer_size => null);
           dbms_output.put_line('P_CEN_BRMP_CHANGE出错！'|| table_list.table_name || table_list.table_cname  || ':->' || Sqlcode || '->' || Sqlerrm);

          End;
          end Loop;

       END P_CEN_BRMP_CHANGE;
       
       
       

       PROCEDURE P_CEN_BRMP_TEMP is
         col_sql      Varchar2(4000) := ''; --动态sql保存
         --cols_str     Varchar2(4000) := ''; --字段名称拼装
         --i_total      Number := 0; --插入成功后影响的数据条数
         --v_msg        Varchar2(1000); --错误信息
         script       Clob;
         pkno         int := 0; --pk字段循环数

         --vext_columns Varchar2(4000);
         --vprc_name    Varchar2(100);

        begin
           --vprc_name := 'P_CEN_BRMP_TEMP';

           For table_list In ( select m.model_name as table_cname,
                                      m.model_tab_name as TABLE_NAME,
                                      (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id) as col,
                                      (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id and md.pk=1 ) as pk
                               from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                               where m.status = 1 and m.audit_status = 9 )
           Loop
               begin


                 /**
                 * 更新ex表，在temp表有新数据的记录 成功标志重新置为0
                 */
                 col_sql := 'update cen_brmp.' || table_list.table_name || '_ex ex set cgbz_1=0 where ex.rowid in ( select a.rowid from cen_brmp.' || table_list.table_name || '_ex a inner join (select ' || table_list.pk || ' from cen_brmp.' || table_list.table_name || '_TEMP b where b.zybz=1 group by ' || table_list.pk ||' ) c ';

                 pkno := 0;
                 For pks in ( select md.model_col_name pk
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                            where md.pk=1
                            and md.model_id = (
                            select m.model_id
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                            where m.status = 1 and m.audit_status = 9
                            and m.model_tab_name = table_list.table_name)
                            )
                  loop
                    if pkno = 0 then
                       col_sql := col_sql || 'on a.'|| pks.pk  || ' = c.'|| pks.pk ;
                    else
                       col_sql := col_sql || 'and a.'|| pks.pk  || ' = c.'|| pks.pk ;
                    end if;
                    pkno := pkno+1;
                  end loop;
                  col_sql := col_sql ||')' ;
                 --dbms_output.put_line(col_sql);
                 execute immediate col_sql;

                 /*
                 * 删除在主题库的重复数据(与临时库比较得出)
                 *
                 *  --可能需要添加 table_list.pk ，根据pk字段，自表去重
                 *  'delete from '|| table_list.table_name || ' where rowid not in ( select max(rowid) from ' || table_list.table_name || ' group by ' || table_list.pk || ') '
                 */

                 col_sql := 'delete from cen_brmp.' || table_list.table_name || ' a1 where a1.rowid in ( select a.rowid from cen_brmp.' || table_list.table_name || ' a inner join (select ' || table_list.pk || ' from cen_brmp.' || table_list.table_name || '_TEMP b where b.zybz=1 group by ' || table_list.pk ||' ) c ';

                 pkno := 0;
                 For pks in ( select md.model_col_name pk
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                            where md.pk=1
                            and md.model_id = (
                            select m.model_id
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                            where m.status = 1 and m.audit_status = 9
                            and m.model_tab_name = table_list.table_name)
                            )
                  loop
                    if pkno = 0 then
                       col_sql := col_sql || 'on a.'|| pks.pk  || ' = c.'|| pks.pk ;
                    else
                       col_sql := col_sql || 'and a.'|| pks.pk  || ' = c.'|| pks.pk ;
                    end if;
                    pkno := pkno+1;
                  end loop;
                  col_sql := col_sql ||')' ;
                 --dbms_output.put_line(col_sql);
                 execute immediate col_sql;


                 /*
                 * 临时表去重插入主题库
                 */
                 script := 'insert into cen_brmp.' || table_list.table_name || '(' || table_list.col || ')'
                        || 'select ' || table_list.col || ' from ' || table_list.table_name
                        || '_TEMP a where a.ZYBZ=1 and a.rowid = (select max(rowid) from cen_brmp.'  || table_list.table_name
                        || '_TEMP b where b.ZYBZ=1 ' ;

                 For pks in ( select md.model_col_name pk
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                            where md.pk=1
                            and md.model_id = (
                            select m.model_id
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                            where m.status = 1 and m.audit_status = 9
                            and m.model_tab_name = table_list.table_name)
                            )
                  loop
                    script := script || 'and a.'|| pks.pk  || ' = b.'|| pks.pk ;
                  end loop;
                  script := script ||')' ;

                 --DBMS_OUTPUT.ENABLE(buffer_size => null);
                 --dbms_output.put_line(script);
                 execute immediate script;


                 /*
                 * 临时表去重插入ex扩展信息表。
                 */
                 script := 'insert into cen_brmp.' || table_list.table_name || '_ex (' || table_list.pk || ')'
                        || 'select ' || table_list.pk || ' from ' || table_list.table_name
                        || '_TEMP a where a.ZYBZ=1 and a.rowid = (select max(rowid) from cen_brmp.'  || table_list.table_name
                        || '_TEMP b where b.ZYBZ=1 ' ;

                 For pks in ( select md.model_col_name pk
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                            where md.pk=1
                            and md.model_id = (
                            select m.model_id
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                            where m.status = 1 and m.audit_status = 9
                            and m.model_tab_name = table_list.table_name)
                            )
                  loop
                    script := script || 'and a.'|| pks.pk  || ' = b.'|| pks.pk ;
                  end loop;
                  script := script ||' ) and a.rowid not in ( select a1.rowid from cen_brmp.'|| table_list.table_name ||'_temp a1 inner join (select '
                         || table_list.pk || ' from ' || table_list.table_name || '_ex b1 group by ' || table_list.pk || ' ) c1 ';

                  pkno := 0;
                  For pks in ( select md.model_col_name pk
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md
                            where md.pk=1
                            and md.model_id = (
                            select m.model_id
                            from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                            where m.status = 1 and m.audit_status = 9
                            and m.model_tab_name = table_list.table_name)
                            )
                  loop
                    if pkno = 0 then
                       script := script || ' on a1.'|| pks.pk  || ' = c1.'|| pks.pk ;
                    else
                       script := script || ' and a1.'|| pks.pk  || ' = c1.'|| pks.pk ;
                    end if;
                       pkno := pkno+1;
                  end loop;
                  script := script ||' ) ';

                  --DBMS_OUTPUT.ENABLE(buffer_size => null);
                  --dbms_output.put_line(script);
                  execute immediate script;


                 --将插入成功的作业标志修改为 2
                 col_sql := 'update cen_brmp.'|| table_list.table_name || '_TEMP set ZYBZ= 2 where ZYBZ=1';
                -- dbms_output.put_line(col_sql);
                 execute immediate col_sql;
                 commit;


                 /*
                 * 删除临时表中，已经成功插入主题库的数据，通过作业标志为 2
                 */
                col_sql := 'delete cen_brmp.'|| table_list.table_name || '_TEMP where ZYBZ= 2';
                -- dbms_output.put_line(col_sql);
                execute immediate col_sql;
                commit;

               
               Exception
                 When Others Then
                 Rollback;
                 DBMS_OUTPUT.ENABLE(buffer_size => null);
                 dbms_output.put_line('P_CEN_BRMP_TEMP出错！'|| table_list.table_name || table_list.table_cname  || ':->' || Sqlcode || '->' || Sqlerrm);

               End;
           end Loop;

        end P_CEN_BRMP_TEMP;
        
        PROCEDURE P_CEN_BRMP_AFTER is
         col_sql      Varchar2(4000) := ''; --动态sql保存
         --script       Clob;
         --pkno         int := 0; --pk字段循环数
         begin
           
         For table_list In ( select m.model_name as table_cname,
                                      m.model_tab_name as TABLE_NAME,
                                      (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id) as col,
                                      (select wm_concat(md.model_col_name) from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MODEL md where md.model_id = m.model_id and md.pk=1 ) as pk
                               from cen_brmp.BRMP_CONF_ORIGIN_SYSTEM_MDBASE m
                               where m.status = 1 and m.audit_status = 9 )
         Loop
         BEGIN
           
              /*
               * 整理正式表重建表索引
               *
               * if to_char(sysdate,'dd') = 15 then
               */
             col_sql := 'analyze table cen_brmp.' || table_list.TABLE_NAME || ' compute statistics' ;
             execute immediate col_sql;
             col_sql := 'analyze table cen_brmp.' || table_list.TABLE_NAME || '_ex compute statistics' ;
             execute immediate col_sql;

              FOR STR3 IN (   select 'CEN_BRMP' owner,t.INDEX_NAME
                              from user_indexes t
                              where t.table_name = table_list.table_name
                              and t.index_name not like '%SYS\_IL0000%' escape '\'

              ) LOOP
              col_sql := 'ALTER INDEX ' || STR3.OWNER || '.' || STR3.INDEX_NAME || ' REBUILD' ;
              execute immediate col_sql;
              END LOOP;
                 
         
           
         Exception
                 When Others Then
                 Rollback;
                 DBMS_OUTPUT.ENABLE(buffer_size => null);
                 dbms_output.put_line('P_CEN_BRMP_AFTER！'|| table_list.table_name || table_list.table_cname  || ':->' || Sqlcode || '->' || Sqlerrm);

          End;
          end Loop;

        end P_CEN_BRMP_AFTER;
        

end S_PKG_EXEBRMPJOB;

/************************************************
*
*  创建 执行存储过程
*
*********************************************/

CREATE OR REPLACE PROCEDURE PRO_BRMP_SCHEDUL AUTHID CURRENT_USER is

begin

       CEN_BRMP.S_PKG_EXEBRMPJOB.P_CEN_BRMP_BEGIN;

       CEN_BRMP.S_PKG_EXEBRMPJOB.P_CEN_BRMP_CHANGE;

       CEN_BRMP.S_PKG_EXEBRMPJOB.P_CEN_BRMP_TEMP;


       if to_char(sysdate,'dd') = 15 then
          CEN_BRMP.S_PKG_EXEBRMPJOB.P_CEN_BRMP_AFTER;
       end if;

Exception
  When Others Then
   Rollback;
   DBMS_OUTPUT.ENABLE(buffer_size => null);
   dbms_output.put_line('PRO_BRMP_SCHEDUL出错！:->' || Sqlcode || '->' || Sqlerrm);


end PRO_BRMP_SCHEDUL;

