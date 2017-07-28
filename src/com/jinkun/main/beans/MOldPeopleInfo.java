
package com.jinkun.main.beans;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 老人信息表
 * @author adminx
 *
 */
@Entity
@Table(name="m_olds_info")
@DynamicUpdate(true)
public class MOldPeopleInfo {

	private Integer id;//主键
	private String userId;//用户ID
	private Long createTime; // 创建时间
	private String name; // 姓名
	private Integer sex; // 性别
	private Long birthday; //生日
	private String address; // 家庭住址
	private String phone; // 家庭电话
	private String linkName; // 紧急联系人
	private String linkPhone; //紧急联系人电话
	private Integer isCensus; //是否户籍所在地
	private String idCard; //省份证
	private String nation; // 民族
	private String workState; // 工作状态
	private String workInfo;  // 具体职业
	private String yanglao; // 居家养老情况
	private String payType; //医疗费用支付类型
	private String payTarget; // 其他方式支付信息
	private String bloodType; //血型
	private String allergy; //过敏史
	private String allergyTarget; // 过敏史信息
	private String expose; //暴露史
	private String exposeTarget; // 暴露史信息
	private String jibing;//疾病
	private String shoushu;//手术
	private String waishang;//外伤
	private String shuxue;//输血
	private String father;//父亲
	private String mother;//母亲
	private String brother;//兄弟
	private String sonSister;//子女
	private String yichuan;//遗传
	private String canji;
	
	private String communityName; // 社区名称
	private String communityId; // 社区名称
	private String cencusAddr;// 户籍地址
	private String oldType; //老人类型
	private Integer hasFile; // 是否建档 0未建档，1已建档
	private String income;// 收入/资金、困难
	private String maritalStatus; // 婚姻状况 ： 未婚、已婚、离异、丧偶
	
	private String fuyao; // 服药
	private String kaiyao;
	private String drugStore;
	private String drugList;
	private String xiyang;
	private String liugan;
	private String feiyan;
	private String otherVaccine;
	private String jiawu;
	private String xiyi;
	private String zhushi;
	private String fushi;
	private String shiwuguomin;
	private String duanlian;
	private String paibian;
	private String qiye;
	private String shuimian;
	private String xiyu;
	private String otherInfo;
	private String fuju;
	private String fujuleixing;
	private String sizhi;
	private String guanjie;
	private String jingmaiquzhang;
	private String shuizhong;
	private String xingzou;
	private String louti;
	private String zhuangkuang;
	private String zhaohu;
	private String shenghuo;
	private String yiliao;
	private String anquan;
	
	
	
	private Integer pensionId;
	private Integer stationId;
	
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getLinkPhone() {
		return linkPhone;
	}
	public void setLinkPhone(String linkPhone) {
		this.linkPhone = linkPhone;
	}
	public Integer getIsCensus() {
		return isCensus;
	}
	public void setIsCensus(Integer isCensus) {
		this.isCensus = isCensus;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getWorkInfo() {
		return workInfo;
	}
	public void setWorkInfo(String workInfo) {
		this.workInfo = workInfo;
	}
	public String getPayTarget() {
		return payTarget;
	}
	public void setPayTarget(String payTarget) {
		this.payTarget = payTarget;
	}
	public String getAllergyTarget() {
		return allergyTarget;
	}
	public void setAllergyTarget(String allergyTarget) {
		this.allergyTarget = allergyTarget;
	}
	public String getExposeTarget() {
		return exposeTarget;
	}
	public void setExposeTarget(String exposeTarget) {
		this.exposeTarget = exposeTarget;
	}
	public String getJibing() {
		return jibing;
	}
	public void setJibing(String jibing) {
		this.jibing = jibing;
	}
	public String getShoushu() {
		return shoushu;
	}
	public void setShoushu(String shoushu) {
		this.shoushu = shoushu;
	}
	public String getWaishang() {
		return waishang;
	}
	public void setWaishang(String waishang) {
		this.waishang = waishang;
	}
	public String getShuxue() {
		return shuxue;
	}
	public void setShuxue(String shuxue) {
		this.shuxue = shuxue;
	}
	public String getFather() {
		return father;
	}
	public void setFather(String father) {
		this.father = father;
	}
	public String getMother() {
		return mother;
	}
	public void setMother(String mother) {
		this.mother = mother;
	}
	public String getBrother() {
		return brother;
	}
	public void setBrother(String brother) {
		this.brother = brother;
	}
	public String getSonSister() {
		return sonSister;
	}
	public void setSonSister(String sonSister) {
		this.sonSister = sonSister;
	}
	public String getYichuan() {
		return yichuan;
	}
	public void setYichuan(String yichuan) {
		this.yichuan = yichuan;
	}
	public String getCanji() {
		return canji;
	}
	public void setCanji(String canji) {
		this.canji = canji;
	}
	public Integer getPensionId() {
		return pensionId;
	}
	public void setPensionId(Integer pensionId) {
		this.pensionId = pensionId;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public Integer getStationId() {
		return stationId;
	}
	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}
	public String getCommunityName() {
		return communityName;
	}
	public void setCommunityName(String communityName) {
		this.communityName = communityName;
	}
	public String getCencusAddr() {
		return cencusAddr;
	}
	public void setCencusAddr(String cencusAddr) {
		this.cencusAddr = cencusAddr;
	}
	public String getOldType() {
		return oldType;
	}
	public void setOldType(String oldType) {
		this.oldType = oldType;
	}
	public Integer getHasFile() {
		return hasFile;
	}
	public void setHasFile(Integer hasFile) {
		this.hasFile = hasFile;
	}
	public String getFuyao() {
		return fuyao;
	}
	public void setFuyao(String fuyao) {
		this.fuyao = fuyao;
	}
	public String getKaiyao() {
		return kaiyao;
	}
	public void setKaiyao(String kaiyao) {
		this.kaiyao = kaiyao;
	}
	public String getDrugStore() {
		return drugStore;
	}
	public void setDrugStore(String drugStore) {
		this.drugStore = drugStore;
	}
	public String getDrugList() {
		return drugList;
	}
	public void setDrugList(String drugList) {
		this.drugList = drugList;
	}
	public String getXiyang() {
		return xiyang;
	}
	public void setXiyang(String xiyang) {
		this.xiyang = xiyang;
	}
	public String getLiugan() {
		return liugan;
	}
	public void setLiugan(String liugan) {
		this.liugan = liugan;
	}
	public String getFeiyan() {
		return feiyan;
	}
	public void setFeiyan(String feiyan) {
		this.feiyan = feiyan;
	}
	public String getOtherVaccine() {
		return otherVaccine;
	}
	public void setOtherVaccine(String otherVaccine) {
		this.otherVaccine = otherVaccine;
	}
	public String getJiawu() {
		return jiawu;
	}
	public void setJiawu(String jiawu) {
		this.jiawu = jiawu;
	}
	public String getXiyi() {
		return xiyi;
	}
	public void setXiyi(String xiyi) {
		this.xiyi = xiyi;
	}
	public String getZhushi() {
		return zhushi;
	}
	public void setZhushi(String zhushi) {
		this.zhushi = zhushi;
	}
	public String getFushi() {
		return fushi;
	}
	public void setFushi(String fushi) {
		this.fushi = fushi;
	}
	public String getShiwuguomin() {
		return shiwuguomin;
	}
	public void setShiwuguomin(String shiwuguomin) {
		this.shiwuguomin = shiwuguomin;
	}
	public String getDuanlian() {
		return duanlian;
	}
	public void setDuanlian(String duanlian) {
		this.duanlian = duanlian;
	}
	public String getPaibian() {
		return paibian;
	}
	public void setPaibian(String paibian) {
		this.paibian = paibian;
	}
	public String getQiye() {
		return qiye;
	}
	public void setQiye(String qiye) {
		this.qiye = qiye;
	}
	public String getShuimian() {
		return shuimian;
	}
	public void setShuimian(String shuimian) {
		this.shuimian = shuimian;
	}
	public String getXiyu() {
		return xiyu;
	}
	public void setXiyu(String xiyu) {
		this.xiyu = xiyu;
	}
	public String getOtherInfo() {
		return otherInfo;
	}
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	public String getFuju() {
		return fuju;
	}
	public void setFuju(String fuju) {
		this.fuju = fuju;
	}
	public String getFujuleixing() {
		return fujuleixing;
	}
	public void setFujuleixing(String fujuleixing) {
		this.fujuleixing = fujuleixing;
	}
	public String getSizhi() {
		return sizhi;
	}
	public void setSizhi(String sizhi) {
		this.sizhi = sizhi;
	}
	public String getGuanjie() {
		return guanjie;
	}
	public void setGuanjie(String guanjie) {
		this.guanjie = guanjie;
	}
	public String getJingmaiquzhang() {
		return jingmaiquzhang;
	}
	public void setJingmaiquzhang(String jingmaiquzhang) {
		this.jingmaiquzhang = jingmaiquzhang;
	}
	public String getShuizhong() {
		return shuizhong;
	}
	public void setShuizhong(String shuizhong) {
		this.shuizhong = shuizhong;
	}
	public String getXingzou() {
		return xingzou;
	}
	public void setXingzou(String xingzou) {
		this.xingzou = xingzou;
	}
	public String getLouti() {
		return louti;
	}
	public void setLouti(String louti) {
		this.louti = louti;
	}
	public String getZhuangkuang() {
		return zhuangkuang;
	}
	public void setZhuangkuang(String zhuangkuang) {
		this.zhuangkuang = zhuangkuang;
	}
	public String getZhaohu() {
		return zhaohu;
	}
	public void setZhaohu(String zhaohu) {
		this.zhaohu = zhaohu;
	}
	public String getShenghuo() {
		return shenghuo;
	}
	public void setShenghuo(String shenghuo) {
		this.shenghuo = shenghuo;
	}
	public String getYiliao() {
		return yiliao;
	}
	public void setYiliao(String yiliao) {
		this.yiliao = yiliao;
	}
	public String getAnquan() {
		return anquan;
	}
	public void setAnquan(String anquan) {
		this.anquan = anquan;
	}
	public String getWorkState() {
		return workState;
	}
	public void setWorkState(String workState) {
		this.workState = workState;
	}
	public String getYanglao() {
		return yanglao;
	}
	public void setYanglao(String yanglao) {
		this.yanglao = yanglao;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getBloodType() {
		return bloodType;
	}
	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	public String getExpose() {
		return expose;
	}
	public void setExpose(String expose) {
		this.expose = expose;
	}
	public String getCommunityId() {
		return communityId;
	}
	public void setCommunityId(String communityId) {
		this.communityId = communityId;
	}
	public String getIncome() {
		return income;
	}
	public void setIncome(String income) {
		this.income = income;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	
	
	
	
	
}
