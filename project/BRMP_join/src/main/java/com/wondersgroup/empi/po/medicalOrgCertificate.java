package com.wondersgroup.empi.po;

import java.util.Date;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.anotation.Table;


/**
 * 医疗机构执业许可证
 *
 */
@Table(name="md_b1aeaba859ef4c8d8bda",cName="医疗机构执业许可证",camel=false)
public class medicalOrgCertificate {
	private int xgbz;
	@ColumnName("ZSID")
	private String Id;
	@ColumnName("ZSID")
	private String originId;
	private Date updateTime;
	
	private String JiGouMingChen;//机构名称 fieldLength\":\"100\"},
	private String DiZhi;//地址 fieldLength\":\"200\"},
	private String ZhenLiaoKeMu;//诊疗科目  fieldLength\":\"1000\"},
	private String FaDingDaiBiaoRen;//法定代表人 fieldLength\":\"100\"},
	private String ZhuYaoFuZeRen;//主要负责人 fieldLength\":\"100\"},
	private String DengJiHao;//登记号 fieldLength\":\"100\"},
	private Date   YouXiaoQiZi;//有效期自
	private Date   YouXiaoQiZhi;//有效期至
	private String FaZhengJiGuan;//发证机关 fieldLength\":\"100\"},
	private Date   FaZhengRiQi;//发证日期
	private String ErWeiMa;//证照编号：[DengJiHao]\",\"fieldDesc\":\"二维码\" \"fieldLength\":\"200\"}]},
	
	
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
	public String getJiGouMingChen() {
		return JiGouMingChen;
	}
	public void setJiGouMingChen(String jiGouMingChen) {
		JiGouMingChen = jiGouMingChen;
	}
	public String getDiZhi() {
		return DiZhi;
	}
	public void setDiZhi(String diZhi) {
		DiZhi = diZhi;
	}
	public String getZhenLiaoKeMu() {
		return ZhenLiaoKeMu;
	}
	public void setZhenLiaoKeMu(String zhenLiaoKeMu) {
		ZhenLiaoKeMu = zhenLiaoKeMu;
	}
	public String getFaDingDaiBiaoRen() {
		return FaDingDaiBiaoRen;
	}
	public void setFaDingDaiBiaoRen(String faDingDaiBiaoRen) {
		FaDingDaiBiaoRen = faDingDaiBiaoRen;
	}
	public String getZhuYaoFuZeRen() {
		return ZhuYaoFuZeRen;
	}
	public void setZhuYaoFuZeRen(String zhuYaoFuZeRen) {
		ZhuYaoFuZeRen = zhuYaoFuZeRen;
	}
	public String getDengJiHao() {
		return DengJiHao;
	}
	public void setDengJiHao(String dengJiHao) {
		DengJiHao = dengJiHao;
	}
	public Date getYouXiaoQiZi() {
		return YouXiaoQiZi;
	}
	public void setYouXiaoQiZi(Date youXiaoQiZi) {
		YouXiaoQiZi = youXiaoQiZi;
	}
	public Date getYouXiaoQiZhi() {
		return YouXiaoQiZhi;
	}
	public void setYouXiaoQiZhi(Date youXiaoQiZhi) {
		YouXiaoQiZhi = youXiaoQiZhi;
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
	public String getErWeiMa() {
		return ErWeiMa;
	}
	public void setErWeiMa(String erWeiMa) {
		//ErWeiMa = erWeiMa;
		ErWeiMa = "";//默认空值 证照数据反馈说传空值
	}
	
}
