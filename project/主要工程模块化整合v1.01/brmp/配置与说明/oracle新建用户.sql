/*创建表空间 */
create tablespace TBS_cen_brmp    logging datafile 'D:\app\oracle\oradata\PDBCENTER\tbs_cen_brmp_01.dbf' size 10M AUTOEXTEND ON NEXT 1G;

/*创建临时表空间*/
create temporary tablespace TBS_TEMP_cen_brmp tempfile 'D:\app\oracle\oradata\PDBCENTER\tbs_tmp_cen_brmp_01.dbf'       size 10M AUTOEXTEND ON NEXT 1G;  

/*创建索引表空间 */
create tablespace TBS_IX_cen_brmp logging datafile 'D:\app\oracle\oradata\PDBCENTER\tbs_ix_cen_brmp_01.dbf'       size 10M AUTOEXTEND ON NEXT 1G; 

/*创建用户*/
CREATE USER cen_brmp  PROFILE "DEFAULT"     IDENTIFIED BY "cen_brmp" DEFAULT TABLESPACE TBS_cen_brmp     TEMPORARY TABLESPACE TBS_TEMP_cen_brmp;

grant CONNECT to cen_brmp;
grant RESOURCE to cen_brmp;
grant SELECT_CATALOG_ROLE to cen_brmp;
grant ALTER ANY TABLE to cen_brmp;
grant ALTER SESSION to cen_brmp;
grant CREATE ANY TABLE to cen_brmp;
grant CREATE ANY VIEW to cen_brmp;
grant CREATE SYNONYM to cen_brmp;
grant DEBUG ANY PROCEDURE to cen_brmp;
grant DEBUG CONNECT SESSION to cen_brmp;
grant INSERT ANY TABLE to cen_brmp;
grant SELECT ANY DICTIONARY to cen_brmp;
grant SELECT ANY TABLE to cen_brmp;
grant UNLIMITED TABLESPACE to cen_brmp;
grant create database link to cen_brmp;
grant CREATE ANY MATERIALIZED VIEW to cen_brmp;
grant GLOBAL QUERY REWRITE to cen_brmp;
GRANT ON COMMIT REFRESH to cen_brmp;
grant dba to cen_brmp;