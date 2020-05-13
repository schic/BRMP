/* 查询所有模型 */
select 'select * from '|| mb.model_tab_name ||'; -- ' || mb.model_name
from cen_brmp.brmp_conf_origin_system_mdbase mb;

/* 查询向河对面网络传入数据成功的情况，成功标志位  */
select 'select '''|| mb.model_name ||''',cgbz_1,count(cgbz_1) from '|| mb.model_tab_name||'_ex group by cgbz_1;'
from cen_brmp.brmp_conf_origin_system_mdbase mb;