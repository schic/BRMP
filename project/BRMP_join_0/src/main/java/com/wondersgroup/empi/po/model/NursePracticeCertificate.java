package com.wondersgroup.empi.po.model;

import java.util.Date;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.anotation.Table;

/**
 * 护士执业证书
 */
@Table(name="gir_06dfc846b882412a9a5d",cName="护士执业证书",camel=false)
public class NursePracticeCertificate {
	
	private int xgbz;
	@ColumnName("ZSID")
	private String Id;
	@ColumnName("ZSID")
	private String originId;
	private Date updateTime;
	
	private String FaZhengJiGuan;//发证机关 fieldLength\":\"50\"},
	private Date   FaZhengRiQi;//发证日期
	private String QianFaRen;//签发人 fieldLength\":\"20\"}],
	private String XingMing;//姓名 fieldLength\":\"50\"},
	private Date   ChuShengRiQi;//出生日期
	private String XingBie;//性别 fieldLength\":\"2\"},
	private String GuoJi;//国籍 fieldLength\":\"100\"},
	private String ZhiYeDiDian;//执业地点 fieldLength\":\"200\"},
	private String HuShiZhiYeZhengShuBianHao;//护士执业证书编号 fieldLength\":\"100\"},
	private String ShenFenZhengHao;//身份证号 fieldLength\":\"50\"},
	private Date   ZhuCeRiQi;//注册日期
	private Date   ZhuCeYouXiaoQiZhi;//注册有效期至
	private Date   YanXuZhuCeRiQiYi;//延续注册日期一
	private Date   YanXuZhuCeYouXiaoQiZhiYi;//延续注册有效期至一
	private Date   YanXuZhuCeRiQiEr;//延续注册日期二
	private Date   YanXuZhuCeYouXiaoQiZhiEr;//延续注册有效期至二
	private Date   YanXuZhuCeRiQiSan;//延续注册日期三
	private Date   YanXuZhuCeYouXiaoQiZhiSan;//延续注册有效期至三
	private Date   YanXuZhuCeRiQiSi;//延续注册日期四
	private Date   YanXuZhuCeYouXiaoQiZhiSi;//延续注册有效期至四
	private Date   BianGengRiQiYi;//变更日期一
	private String BianGengShiXiangYi;//变更事项一 fieldLength\":\"200\"},
	private Date   BianGengRiQiEr;//变更日期二
	private String BianGengShiXiangEr;//变更事项二 fieldLength\":\"200\"},
	private Date   BianGengRiQiSan;//变更日期三
	private String BianGengShiXiangSan;//变更事项三
	private Date   BianGengRiQiSi;//变更日期四
	private String BianGengShiXiangSi;//变更事项四 fieldLength\":\"200\"}]
	
	
	public int getXgbz() {
		return xgbz;
	}
	public void setXgbz(int xgbz) {
		this.xgbz = xgbz;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getOriginId() {
		return originId;
	}
	public void setOriginId(String originId) {
		this.originId = originId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getFaZhengJiGuan() {
		return FaZhengJiGuan;
	}
	public void setFaZhengJiGuan(String faZhengJiGuan) {
		FaZhengJiGuan = faZhengJiGuan;
	}
	public Date getFaZhengRiQi() {
		return FaZhengRiQi;
	}
	public void setFaZhengRiQi(Date faZhengRiQi) {
		FaZhengRiQi = faZhengRiQi;
	}
	public String getQianFaRen() {
		return QianFaRen;
	}
	public void setQianFaRen(String qianFaRen) {
		QianFaRen = qianFaRen;
	}
	public String getXingMing() {
		return XingMing;
	}
	public void setXingMing(String xingMing) {
		XingMing = xingMing;
	}
	public Date getChuShengRiQi() {
		return ChuShengRiQi;
	}
	public void setChuShengRiQi(Date chuShengRiQi) {
		ChuShengRiQi = chuShengRiQi;
	}
	public String getXingBie() {
		return XingBie;
	}
	public void setXingBie(String xingBie) {
		XingBie = xingBie;
	}
	public String getGuoJi() {
		return GuoJi;
	}
	public void setGuoJi(String guoJi) {
		GuoJi = guoJi;
	}
	public String getZhiYeDiDian() {
		return ZhiYeDiDian;
	}
	public void setZhiYeDiDian(String zhiYeDiDian) {
		ZhiYeDiDian = zhiYeDiDian;
	}
	public String getHuShiZhiYeZhengShuBianHao() {
		return HuShiZhiYeZhengShuBianHao;
	}
	public void setHuShiZhiYeZhengShuBianHao(String huShiZhiYeZhengShuBianHao) {
		HuShiZhiYeZhengShuBianHao = huShiZhiYeZhengShuBianHao;
	}
	public String getShenFenZhengHao() {
		return ShenFenZhengHao;
	}
	public void setShenFenZhengHao(String shenFenZhengHao) {
		ShenFenZhengHao = shenFenZhengHao;
	}
	public Date getZhuCeRiQi() {
		return ZhuCeRiQi;
	}
	public void setZhuCeRiQi(Date zhuCeRiQi) {
		ZhuCeRiQi = zhuCeRiQi;
	}
	public Date getZhuCeYouXiaoQiZhi() {
		return ZhuCeYouXiaoQiZhi;
	}
	public void setZhuCeYouXiaoQiZhi(Date zhuCeYouXiaoQiZhi) {
		ZhuCeYouXiaoQiZhi = zhuCeYouXiaoQiZhi;
	}
	public Date getYanXuZhuCeRiQiYi() {
		return YanXuZhuCeRiQiYi;
	}
	public void setYanXuZhuCeRiQiYi(Date yanXuZhuCeRiQiYi) {
		YanXuZhuCeRiQiYi = yanXuZhuCeRiQiYi;
	}
	public Date getYanXuZhuCeYouXiaoQiZhiYi() {
		return YanXuZhuCeYouXiaoQiZhiYi;
	}
	public void setYanXuZhuCeYouXiaoQiZhiYi(Date yanXuZhuCeYouXiaoQiZhiYi) {
		YanXuZhuCeYouXiaoQiZhiYi = yanXuZhuCeYouXiaoQiZhiYi;
	}
	public Date getYanXuZhuCeRiQiEr() {
		return YanXuZhuCeRiQiEr;
	}
	public void setYanXuZhuCeRiQiEr(Date yanXuZhuCeRiQiEr) {
		YanXuZhuCeRiQiEr = yanXuZhuCeRiQiEr;
	}
	public Date getYanXuZhuCeYouXiaoQiZhiEr() {
		return YanXuZhuCeYouXiaoQiZhiEr;
	}
	public void setYanXuZhuCeYouXiaoQiZhiEr(Date yanXuZhuCeYouXiaoQiZhiEr) {
		YanXuZhuCeYouXiaoQiZhiEr = yanXuZhuCeYouXiaoQiZhiEr;
	}
	public Date getYanXuZhuCeRiQiSan() {
		return YanXuZhuCeRiQiSan;
	}
	public void setYanXuZhuCeRiQiSan(Date yanXuZhuCeRiQiSan) {
		YanXuZhuCeRiQiSan = yanXuZhuCeRiQiSan;
	}
	public Date getYanXuZhuCeYouXiaoQiZhiSan() {
		return YanXuZhuCeYouXiaoQiZhiSan;
	}
	public void setYanXuZhuCeYouXiaoQiZhiSan(Date yanXuZhuCeYouXiaoQiZhiSan) {
		YanXuZhuCeYouXiaoQiZhiSan = yanXuZhuCeYouXiaoQiZhiSan;
	}
	public Date getYanXuZhuCeRiQiSi() {
		return YanXuZhuCeRiQiSi;
	}
	public void setYanXuZhuCeRiQiSi(Date yanXuZhuCeRiQiSi) {
		YanXuZhuCeRiQiSi = yanXuZhuCeRiQiSi;
	}
	public Date getYanXuZhuCeYouXiaoQiZhiSi() {
		return YanXuZhuCeYouXiaoQiZhiSi;
	}
	public void setYanXuZhuCeYouXiaoQiZhiSi(Date yanXuZhuCeYouXiaoQiZhiSi) {
		YanXuZhuCeYouXiaoQiZhiSi = yanXuZhuCeYouXiaoQiZhiSi;
	}
	public Date getBianGengRiQiYi() {
		return BianGengRiQiYi;
	}
	public void setBianGengRiQiYi(Date bianGengRiQiYi) {
		BianGengRiQiYi = bianGengRiQiYi;
	}
	public String getBianGengShiXiangYi() {
		return BianGengShiXiangYi;
	}
	public void setBianGengShiXiangYi(String bianGengShiXiangYi) {
		BianGengShiXiangYi = bianGengShiXiangYi;
	}
	public Date getBianGengRiQiEr() {
		return BianGengRiQiEr;
	}
	public void setBianGengRiQiEr(Date bianGengRiQiEr) {
		BianGengRiQiEr = bianGengRiQiEr;
	}
	public String getBianGengShiXiangEr() {
		return BianGengShiXiangEr;
	}
	public void setBianGengShiXiangEr(String bianGengShiXiangEr) {
		BianGengShiXiangEr = bianGengShiXiangEr;
	}
	public Date getBianGengRiQiSan() {
		return BianGengRiQiSan;
	}
	public void setBianGengRiQiSan(Date bianGengRiQiSan) {
		BianGengRiQiSan = bianGengRiQiSan;
	}
	public String getBianGengShiXiangSan() {
		return BianGengShiXiangSan;
	}
	public void setBianGengShiXiangSan(String bianGengShiXiangSan) {
		BianGengShiXiangSan = bianGengShiXiangSan;
	}
	public Date getBianGengRiQiSi() {
		return BianGengRiQiSi;
	}
	public void setBianGengRiQiSi(Date bianGengRiQiSi) {
		BianGengRiQiSi = bianGengRiQiSi;
	}
	public String getBianGengShiXiangSi() {
		return BianGengShiXiangSi;
	}
	public void setBianGengShiXiangSi(String bianGengShiXiangSi) {
		BianGengShiXiangSi = bianGengShiXiangSi;
	}
	
}
