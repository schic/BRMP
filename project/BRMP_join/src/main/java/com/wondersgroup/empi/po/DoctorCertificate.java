package com.wondersgroup.empi.po;

import java.util.Date;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.anotation.Table;


/**
 * 医师执业证书
 * 
 */
@Table(name="md_da0bb1cd29a8433f9c3d",cName="医师执业证书",camel=false)
public class DoctorCertificate {
	private int xgbz;
	@ColumnName("ZSID")
	private String Id;
	@ColumnName("ZSID")
	private String originId;
	private Date updateTime;
	
	private String XingMing;//姓名 fieldLength\":\"50\"},
	private String XingBie;//性别 fieldLength\":\"20\"},
	private String ShenFenZhengHao;//身份证号 fieldLength\":\"50\"},
	
	private String ZhengShuBianMa;//证书编码 fieldLength\":\"50\"},
	private Date   QianFaRiQi;//签发日期
	private String YiShiZiGeZhengShuBianMa;//医师资格证书编码 fieldLength\":\"50\"},
	private String ZhiYeLeiBie;//执业类别 fieldLength\":\"50\"},
	private String ZhiYeFanWei;//执业范围 fieldLength\":\"200\"},
	private String ZhiYeDiDian;//执业地点 fieldLength\":\"200\"},
	private String FaZhengJiGuan;//发证机关 fieldLength\":\"100\"}],
	private String BianGengXiangMuYi;//变更项目一 fieldLength\":\"200\"},
	private Date   BianGengRiQiYi;//变更日期一
	private String PiZhunJiGuanYi;//批准机关一  fieldLength\":\"50\"},
	private String BianGengXiangMuEr;//变更项目二 fieldLength\":\"200\"},
	private Date   BianGengRiQiEr;//变更日期二
	private String PiZhunJiGuanEr;//批准机关二 fieldLength\":\"50\"},
	private String BianGengXiangMuSan;//变更项目三 fieldLength\":\"200\"},
	private Date   BianGengRiQiSan;//变更日期三
	private String PiZhunJiGuanSan;//批准机关三 fieldLength\":\"50\"},
	private String BianGengXiangMuSi;//变更项目四 fieldLength\":\"200\"},
	private Date   BianGengRiQiSi;//变更日期四
	private String PiZhunJiGuanSi;//批准机关四 fieldLength\":\"50\"}],
	private String BeiZhu;//备注 fieldLength\":\"1000\"}
	
	
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
	public String getXingMing() {
		return XingMing;
	}
	public void setXingMing(String xingMing) {
		XingMing = xingMing;
	}
	public String getXingBie() {
		return XingBie;
	}
	public void setXingBie(String xingBie) {
		XingBie = xingBie;
	}
	public String getShenFenZhengHao() {
		return ShenFenZhengHao;
	}
	public void setShenFenZhengHao(String shenFenZhengHao) {
		ShenFenZhengHao = shenFenZhengHao;
	}
	public String getZhengShuBianMa() {
		return ZhengShuBianMa;
	}
	public void setZhengShuBianMa(String zhengShuBianMa) {
		ZhengShuBianMa = zhengShuBianMa;
	}
	public Date getQianFaRiQi() {
		return QianFaRiQi;
	}
	public void setQianFaRiQi(Date qianFaRiQi) {
		QianFaRiQi = qianFaRiQi;
	}
	public String getYiShiZiGeZhengShuBianMa() {
		return YiShiZiGeZhengShuBianMa;
	}
	public void setYiShiZiGeZhengShuBianMa(String yiShiZiGeZhengShuBianMa) {
		YiShiZiGeZhengShuBianMa = yiShiZiGeZhengShuBianMa;
	}
	public String getZhiYeLeiBie() {
		return ZhiYeLeiBie;
	}
	public void setZhiYeLeiBie(String zhiYeLeiBie) {
		ZhiYeLeiBie = zhiYeLeiBie;
	}
	public String getZhiYeFanWei() {
		return ZhiYeFanWei;
	}
	public void setZhiYeFanWei(String zhiYeFanWei) {
		ZhiYeFanWei = zhiYeFanWei;
	}
	public String getZhiYeDiDian() {
		return ZhiYeDiDian;
	}
	public void setZhiYeDiDian(String zhiYeDiDian) {
		ZhiYeDiDian = zhiYeDiDian;
	}
	public String getFaZhengJiGuan() {
		return FaZhengJiGuan;
	}
	public void setFaZhengJiGuan(String faZhengJiGuan) {
		FaZhengJiGuan = faZhengJiGuan;
	}
	public String getBianGengXiangMuYi() {
		return BianGengXiangMuYi;
	}
	public void setBianGengXiangMuYi(String bianGengXiangMuYi) {
		BianGengXiangMuYi = bianGengXiangMuYi;
	}
	public Date getBianGengRiQiYi() {
		return BianGengRiQiYi;
	}
	public void setBianGengRiQiYi(Date bianGengRiQiYi) {
		BianGengRiQiYi = bianGengRiQiYi;
	}
	public String getPiZhunJiGuanYi() {
		return PiZhunJiGuanYi;
	}
	public void setPiZhunJiGuanYi(String piZhunJiGuanYi) {
		PiZhunJiGuanYi = piZhunJiGuanYi;
	}
	public String getBianGengXiangMuEr() {
		return BianGengXiangMuEr;
	}
	public void setBianGengXiangMuEr(String bianGengXiangMuEr) {
		BianGengXiangMuEr = bianGengXiangMuEr;
	}
	public Date getBianGengRiQiEr() {
		return BianGengRiQiEr;
	}
	public void setBianGengRiQiEr(Date bianGengRiQiEr) {
		BianGengRiQiEr = bianGengRiQiEr;
	}
	public String getPiZhunJiGuanEr() {
		return PiZhunJiGuanEr;
	}
	public void setPiZhunJiGuanEr(String piZhunJiGuanEr) {
		PiZhunJiGuanEr = piZhunJiGuanEr;
	}
	public String getBianGengXiangMuSan() {
		return BianGengXiangMuSan;
	}
	public void setBianGengXiangMuSan(String bianGengXiangMuSan) {
		BianGengXiangMuSan = bianGengXiangMuSan;
	}
	public Date getBianGengRiQiSan() {
		return BianGengRiQiSan;
	}
	public void setBianGengRiQiSan(Date bianGengRiQiSan) {
		BianGengRiQiSan = bianGengRiQiSan;
	}
	public String getPiZhunJiGuanSan() {
		return PiZhunJiGuanSan;
	}
	public void setPiZhunJiGuanSan(String piZhunJiGuanSan) {
		PiZhunJiGuanSan = piZhunJiGuanSan;
	}
	public String getBianGengXiangMuSi() {
		return BianGengXiangMuSi;
	}
	public void setBianGengXiangMuSi(String bianGengXiangMuSi) {
		BianGengXiangMuSi = bianGengXiangMuSi;
	}
	public Date getBianGengRiQiSi() {
		return BianGengRiQiSi;
	}
	public void setBianGengRiQiSi(Date bianGengRiQiSi) {
		BianGengRiQiSi = bianGengRiQiSi;
	}
	public String getPiZhunJiGuanSi() {
		return PiZhunJiGuanSi;
	}
	public void setPiZhunJiGuanSi(String piZhunJiGuanSi) {
		PiZhunJiGuanSi = piZhunJiGuanSi;
	}
	public String getBeiZhu() {
		return BeiZhu;
	}
	public void setBeiZhu(String beiZhu) {
		BeiZhu = beiZhu;
	}
	

}
