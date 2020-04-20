package com.wondersgroup.empi.po.model;

import java.util.Date;

import com.wondersgroup.empi.util.anotation.ColumnName;
import com.wondersgroup.empi.util.anotation.Table;

/**
 * 动态变化传入数据类样例
 * 
 * 注意使用Double类，才能获取对象。
 */
@Table(name="irpt_departments_xz",cName="卫统机构表",camel=false)
public class WtOrg {
	
	private int	xgbz;
	@ColumnName("INDEX_")
	private String	Id;
	@ColumnName("ID")
	private String	originId;
	@ColumnName("")
	private Date	updateTime = new Date();
	private String	CAPTION;
	private String	PARENT;
	private String	DESCRIPTION;
	private String	GOVERNOR;
	private String	TEL;
	private String	ISJC;
	private long	ENABLED;
	private String	UPID0;
	private String	UPID1;
	private String	UPID2;
	private String	UPID3;
	private String	UPID4;
	private String	UPID5;
	private String	UPID6;
	private String	UPID7;
	private String	UPID8;
	private String	UPID9;
	private String	DBH;
	private String	JGBD;
	private String	DEPT_CODE;
	private String	JGFLGL;
	private String	JJLX;
	private String	DEPT_ADRRESSCODE;
	private String	DEPT_CLASS;
	private String	DEPT_XZJDCODE;
	private String	TX_DZ;
	private String	TX_YZBM;
	private String	TX_DHHM;
	private String	TX_EMAIL;
	private String	TX_WEBSITE;
	private String	CLSJ;
	private String	FZR;
	private Double	ZCZJ;
	private String	YYDJ_J;
	private String	YYDJ_D;
	private String	ZBDW;
	private String	LSGX;
	private String	FZJG;
	private String	PZWH;
	private String	DJPZJG;
	private String	BZRQ;
	private String	ZFRQ;
	private String	JBR;
	private String	LLR;
	private Date	CJSJ;
	private String	DBH2;
	private String	DBH3;
	private String	DEPT_CDBJG;
	private Date	LASTUPDATE_;
	private String	FROMDATE_;
	private String	TODATE_;
	private String	DEPT_ZSDBJG;
	private Date	CREATETIME_;
	private String	LOCKOWNER_;
	private String	SYCW;
	private String	JSFP01;
	private String	ISJZ;
	private String	DEPT_CWHCODE;
	private String	YYDJ_PZWH;
	private String	YYDJ_PZWH_TIME;
	private Double	A41;
	private String	PTYLJGDM;
	private long	INDEX_;
	@ColumnName("ID")
	private String  ID_ORG;
	
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
	public String getCAPTION() {
		return CAPTION;
	}
	public void setCAPTION(String cAPTION) {
		CAPTION = cAPTION;
	}
	public String getPARENT() {
		return PARENT;
	}
	public void setPARENT(String pARENT) {
		PARENT = pARENT;
	}
	public String getDESCRIPTION() {
		return DESCRIPTION;
	}
	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}
	public String getGOVERNOR() {
		return GOVERNOR;
	}
	public void setGOVERNOR(String gOVERNOR) {
		GOVERNOR = gOVERNOR;
	}
	public String getTEL() {
		return TEL;
	}
	public void setTEL(String tEL) {
		TEL = tEL;
	}
	public String getISJC() {
		return ISJC;
	}
	public void setISJC(String iSJC) {
		ISJC = iSJC;
	}
	public long getENABLED() {
		return ENABLED;
	}
	public void setENABLED(long eNABLED) {
		ENABLED = eNABLED;
	}
	public String getUPID0() {
		return UPID0;
	}
	public void setUPID0(String uPID0) {
		UPID0 = uPID0;
	}
	public String getUPID1() {
		return UPID1;
	}
	public void setUPID1(String uPID1) {
		UPID1 = uPID1;
	}
	public String getUPID2() {
		return UPID2;
	}
	public void setUPID2(String uPID2) {
		UPID2 = uPID2;
	}
	public String getUPID3() {
		return UPID3;
	}
	public void setUPID3(String uPID3) {
		UPID3 = uPID3;
	}
	public String getUPID4() {
		return UPID4;
	}
	public void setUPID4(String uPID4) {
		UPID4 = uPID4;
	}
	public String getUPID5() {
		return UPID5;
	}
	public void setUPID5(String uPID5) {
		UPID5 = uPID5;
	}
	public String getUPID6() {
		return UPID6;
	}
	public void setUPID6(String uPID6) {
		UPID6 = uPID6;
	}
	public String getUPID7() {
		return UPID7;
	}
	public void setUPID7(String uPID7) {
		UPID7 = uPID7;
	}
	public String getUPID8() {
		return UPID8;
	}
	public void setUPID8(String uPID8) {
		UPID8 = uPID8;
	}
	public String getUPID9() {
		return UPID9;
	}
	public void setUPID9(String uPID9) {
		UPID9 = uPID9;
	}
	public String getDBH() {
		return DBH;
	}
	public void setDBH(String dBH) {
		DBH = dBH;
	}
	public String getJGBD() {
		return JGBD;
	}
	public void setJGBD(String jGBD) {
		JGBD = jGBD;
	}
	public String getDEPT_CODE() {
		return DEPT_CODE;
	}
	public void setDEPT_CODE(String dEPT_CODE) {
		DEPT_CODE = dEPT_CODE;
	}
	public String getJGFLGL() {
		return JGFLGL;
	}
	public void setJGFLGL(String jGFLGL) {
		JGFLGL = jGFLGL;
	}
	public String getJJLX() {
		return JJLX;
	}
	public void setJJLX(String jJLX) {
		JJLX = jJLX;
	}
	public String getDEPT_ADRRESSCODE() {
		return DEPT_ADRRESSCODE;
	}
	public void setDEPT_ADRRESSCODE(String dEPT_ADRRESSCODE) {
		DEPT_ADRRESSCODE = dEPT_ADRRESSCODE;
	}
	public String getDEPT_CLASS() {
		return DEPT_CLASS;
	}
	public void setDEPT_CLASS(String dEPT_CLASS) {
		DEPT_CLASS = dEPT_CLASS;
	}
	public String getDEPT_XZJDCODE() {
		return DEPT_XZJDCODE;
	}
	public void setDEPT_XZJDCODE(String dEPT_XZJDCODE) {
		DEPT_XZJDCODE = dEPT_XZJDCODE;
	}
	public String getTX_DZ() {
		return TX_DZ;
	}
	public void setTX_DZ(String tX_DZ) {
		TX_DZ = tX_DZ;
	}
	public String getTX_YZBM() {
		return TX_YZBM;
	}
	public void setTX_YZBM(String tX_YZBM) {
		TX_YZBM = tX_YZBM;
	}
	public String getTX_DHHM() {
		return TX_DHHM;
	}
	public void setTX_DHHM(String tX_DHHM) {
		TX_DHHM = tX_DHHM;
	}
	public String getTX_EMAIL() {
		return TX_EMAIL;
	}
	public void setTX_EMAIL(String tX_EMAIL) {
		TX_EMAIL = tX_EMAIL;
	}
	public String getTX_WEBSITE() {
		return TX_WEBSITE;
	}
	public void setTX_WEBSITE(String tX_WEBSITE) {
		TX_WEBSITE = tX_WEBSITE;
	}
	public String getCLSJ() {
		return CLSJ;
	}
	public void setCLSJ(String cLSJ) {
		CLSJ = cLSJ;
	}
	public String getFZR() {
		return FZR;
	}
	public void setFZR(String fZR) {
		FZR = fZR;
	}
	public Double getZCZJ() {
		return ZCZJ;
	}
	public void setZCZJ(Double zCZJ) {
		ZCZJ = zCZJ;
	}
	public String getYYDJ_J() {
		return YYDJ_J;
	}
	public void setYYDJ_J(String yYDJ_J) {
		YYDJ_J = yYDJ_J;
	}
	public String getYYDJ_D() {
		return YYDJ_D;
	}
	public void setYYDJ_D(String yYDJ_D) {
		YYDJ_D = yYDJ_D;
	}
	public String getZBDW() {
		return ZBDW;
	}
	public void setZBDW(String zBDW) {
		ZBDW = zBDW;
	}
	public String getLSGX() {
		return LSGX;
	}
	public void setLSGX(String lSGX) {
		LSGX = lSGX;
	}
	public String getFZJG() {
		return FZJG;
	}
	public void setFZJG(String fZJG) {
		FZJG = fZJG;
	}
	public String getPZWH() {
		return PZWH;
	}
	public void setPZWH(String pZWH) {
		PZWH = pZWH;
	}
	public String getDJPZJG() {
		return DJPZJG;
	}
	public void setDJPZJG(String dJPZJG) {
		DJPZJG = dJPZJG;
	}
	public String getBZRQ() {
		return BZRQ;
	}
	public void setBZRQ(String bZRQ) {
		BZRQ = bZRQ;
	}
	public String getZFRQ() {
		return ZFRQ;
	}
	public void setZFRQ(String zFRQ) {
		ZFRQ = zFRQ;
	}
	public String getJBR() {
		return JBR;
	}
	public void setJBR(String jBR) {
		JBR = jBR;
	}
	public String getLLR() {
		return LLR;
	}
	public void setLLR(String lLR) {
		LLR = lLR;
	}
	public Date getCJSJ() {
		return CJSJ;
	}
	public void setCJSJ(Date cJSJ) {
		CJSJ = cJSJ;
	}
	public String getDBH2() {
		return DBH2;
	}
	public void setDBH2(String dBH2) {
		DBH2 = dBH2;
	}
	public String getDBH3() {
		return DBH3;
	}
	public void setDBH3(String dBH3) {
		DBH3 = dBH3;
	}
	public String getDEPT_CDBJG() {
		return DEPT_CDBJG;
	}
	public void setDEPT_CDBJG(String dEPT_CDBJG) {
		DEPT_CDBJG = dEPT_CDBJG;
	}
	public Date getLASTUPDATE_() {
		return LASTUPDATE_;
	}
	public void setLASTUPDATE_(Date lASTUPDATE_) {
		LASTUPDATE_ = lASTUPDATE_;
	}
	public String getFROMDATE_() {
		return FROMDATE_;
	}
	public void setFROMDATE_(String fROMDATE_) {
		FROMDATE_ = fROMDATE_;
	}
	public String getTODATE_() {
		return TODATE_;
	}
	public void setTODATE_(String tODATE_) {
		TODATE_ = tODATE_;
	}
	public String getDEPT_ZSDBJG() {
		return DEPT_ZSDBJG;
	}
	public void setDEPT_ZSDBJG(String dEPT_ZSDBJG) {
		DEPT_ZSDBJG = dEPT_ZSDBJG;
	}
	public Date getCREATETIME_() {
		return CREATETIME_;
	}
	public void setCREATETIME_(Date cREATETIME_) {
		CREATETIME_ = cREATETIME_;
	}
	public String getLOCKOWNER_() {
		return LOCKOWNER_;
	}
	public void setLOCKOWNER_(String lOCKOWNER_) {
		LOCKOWNER_ = lOCKOWNER_;
	}
	public String getSYCW() {
		return SYCW;
	}
	public void setSYCW(String sYCW) {
		SYCW = sYCW;
	}
	public String getJSFP01() {
		return JSFP01;
	}
	public void setJSFP01(String jSFP01) {
		JSFP01 = jSFP01;
	}
	public String getISJZ() {
		return ISJZ;
	}
	public void setISJZ(String iSJZ) {
		ISJZ = iSJZ;
	}
	public String getDEPT_CWHCODE() {
		return DEPT_CWHCODE;
	}
	public void setDEPT_CWHCODE(String dEPT_CWHCODE) {
		DEPT_CWHCODE = dEPT_CWHCODE;
	}
	public String getYYDJ_PZWH() {
		return YYDJ_PZWH;
	}
	public void setYYDJ_PZWH(String yYDJ_PZWH) {
		YYDJ_PZWH = yYDJ_PZWH;
	}
	public String getYYDJ_PZWH_TIME() {
		return YYDJ_PZWH_TIME;
	}
	public void setYYDJ_PZWH_TIME(String yYDJ_PZWH_TIME) {
		YYDJ_PZWH_TIME = yYDJ_PZWH_TIME;
	}
	public Double getA41() {
		return A41;
	}
	public void setA41(Double a41) {
		A41 = a41;
	}
	public String getPTYLJGDM() {
		return PTYLJGDM;
	}
	public void setPTYLJGDM(String pTYLJGDM) {
		PTYLJGDM = pTYLJGDM;
	}
	public long getINDEX_() {
		return INDEX_;
	}
	public void setINDEX_(long iNDEX_) {
		INDEX_ = iNDEX_;
	}
	
	
}