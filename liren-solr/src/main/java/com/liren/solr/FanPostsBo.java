package com.liren.solr;

import java.io.Serializable;
import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

public class FanPostsBo implements Serializable {

    private static final long serialVersionUID = 1L;
	
	@Field
    private Long id;

	@Field
    private Long starId;

	@Field
    private String starName;

	@Field
    private Long fanId;
    
	@Field
    private String title;

	@Field
    private String content;
    
	@Field
    private String detailContent;

	@Field
    private Date publishTime;

	@Field
    private Integer isTop;

	@Field
    private Integer isReport;

	@Field
    private Integer postUserType;

	@Field
    private Integer isAnonymous;

	@Field
    private Integer isNeedAudit;

	@Field
    private Integer isAudit;

	@Field
    private Integer auditScore;

	@Field
    private Integer isDelete;

	@Field
    private Integer isSelfDelete;

	@Field
    private Integer isRecommend;

	@Field
    private Date recommendDate;

	@Field
    private Integer isCream;

	@Field
    private Long topTime;

	@Field
    private Long creamTime;

	@Field
    private Long startIndex;

	@Field
    private Date createTime;

	@Field
    private Date updateTime;

	@Field
    private Long createBy;

	@Field
    private Long updateBy;
    
	@Field
    private Integer praiseNum;

	@Field
    private Integer commentNum;
    
	@Field
    private String fanNickName;
    
	@Field
    private String fanPortrait;
	
	private Integer creamType;

    private Integer topType;
    
    //分享url
    private String shareUrl;
    
    //分享title
    private String shareTitle;
    
    //是否圈主发送的动态
    private Integer isHeaderSend; 
    
    //明星名字
    private String starRealName;
    
    //圈子名称
    private String ringName;
    
    private String latestPraiser;
    
    private Long interval;//时间间隔，单位：秒
    
    @Field
    private Long ringId;
    
    private Integer isStar;//0为否，1为是
    
    private Integer isHot;
    
    private Integer isStarStation;//是否为明星小站
    
    private String starStationName;//小站名称
    
    private Long stationId;//小站Id
    
    private Integer isOpDel = 0;//帖子是否能删除,0为否，1为是
    
    private Integer fanSex;
    
    private String honorTag; //用户标签
    
    private Long hotPostId;

	public Long getRingId() { 
		return ringId;
	}

	public void setRingId(Long ringId) {
		this.ringId = ringId;
	}


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStarId() {
        return starId;
    }

    public void setStarId(Long starId) {
        this.starId = starId;
    }

    public String getStarName() {
        return starName;
    }

    public void setStarName(String starName) {
        this.starName = starName == null ? null : starName.trim();
    }

    public Long getFanId() {
        return fanId;
    }

    public void setFanId(Long fanId) {
        this.fanId = fanId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getIsReport() {
        return isReport;
    }

    public void setIsReport(Integer isReport) {
        this.isReport = isReport;
    }

    public Integer getPostUserType() {
        return postUserType;
    }

    public void setPostUserType(Integer postUserType) {
        this.postUserType = postUserType;
    }

    public Integer getIsAnonymous() {
        return isAnonymous;
    }

    public void setIsAnonymous(Integer isAnonymous) {
        this.isAnonymous = isAnonymous;
    }

    public Integer getIsNeedAudit() {
        return isNeedAudit;
    }

    public void setIsNeedAudit(Integer isNeedAudit) {
        this.isNeedAudit = isNeedAudit;
    }

    public Integer getIsAudit() {
        return isAudit;
    }

    public void setIsAudit(Integer isAudit) {
        this.isAudit = isAudit;
    }

    public Integer getAuditScore() {
        return auditScore;
    }

    public void setAuditScore(Integer auditScore) {
        this.auditScore = auditScore;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getIsSelfDelete() {
        return isSelfDelete;
    }

    public void setIsSelfDelete(Integer isSelfDelete) {
        this.isSelfDelete = isSelfDelete;
    }

    public Integer getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(Integer isRecommend) {
        this.isRecommend = isRecommend;
    }

    public Date getRecommendDate() {
        return recommendDate;
    }

    public void setRecommendDate(Date recommendDate) {
        this.recommendDate = recommendDate;
    }

    public Integer getIsCream() {
        return isCream;
    }

    public void setIsCream(Integer isCream) {
        this.isCream = isCream;
    }

    public Long getTopTime() {
        return topTime;
    }

    public void setTopTime(Long topTime) {
        this.topTime = topTime;
    }

    public Long getCreamTime() {
        return creamTime;
    }

    public void setCreamTime(Long creamTime) {
        this.creamTime = creamTime;
    }

    public Long getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public Long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Long updateBy) {
        this.updateBy = updateBy;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

	public Integer getPraiseNum() {
		return praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public String getFanNickName() {
		return fanNickName;
	}

	public void setFanNickName(String fanNickName) {
		this.fanNickName = fanNickName;
	}

	public String getFanPortrait() {
		return fanPortrait;
	}

	public void setFanPortrait(String fanPortrait) {
		this.fanPortrait = fanPortrait;
	}

	public String getShareUrl() {
		return shareUrl;
	}

	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}

	public String getShareTitle() {
		return shareTitle;
	}

	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}

	public Integer getIsHeaderSend() {
		return isHeaderSend;
	}

	public void setIsHeaderSend(Integer isHeaderSend) {
		this.isHeaderSend = isHeaderSend;
	}

	public String getStarRealName() {
		return starRealName;
	}

	public void setStarRealName(String starRealName) {
		this.starRealName = starRealName;
	}

	public String getLatestPraiser() {
		return latestPraiser;
	}

	public void setLatestPraiser(String latestPraiser) {
		this.latestPraiser = latestPraiser;
	}

	public String getDetailContent() {
		return detailContent;
	}

	public void setDetailContent(String detailContent) {
		this.detailContent = detailContent;
	}
	
	

	public String getRingName() {
		return ringName;
	}

	public void setRingName(String ringName) {
		this.ringName = ringName;
	}

	public Long getInterval() {
		return interval;
	}

	public void setInterval(Long interval) {
		this.interval = interval;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsStar() {
		return isStar;
	}

	public void setIsStar(Integer isStar) {
		this.isStar = isStar;
	}
	
	public Integer getIsStarStation() {
		return isStarStation;
	}

	public void setIsStarStation(Integer isStarStation) {
		this.isStarStation = isStarStation;
	}

	public String getStarStationName() {
		return starStationName;
	}

	public void setStarStationName(String starStationName) {
		this.starStationName = starStationName;
	}

	public Integer getIsOpDel() {
		return isOpDel;
	}

	public void setIsOpDel(Integer isOpDel) {
		this.isOpDel = isOpDel;
	}

	public Long getStationId() {
		return stationId;
	}

	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}

	public Integer getFanSex() {
		return fanSex;
	}

	public void setFanSex(Integer fanSex) {
		this.fanSex = fanSex;
	}

	public Integer getCreamType() {
		return creamType;
	}

	public void setCreamType(Integer creamType) {
		this.creamType = creamType;
	}

	public Integer getTopType() {
		return topType;
	}

	public void setTopType(Integer topType) {
		this.topType = topType;
	}

	public String getHonorTag() {
		return honorTag;
	}

	public void setHonorTag(String honorTag) {
		this.honorTag = honorTag;
	}

	public Long getHotPostId() {
		return hotPostId;
	}

	public void setHotPostId(Long hotPostId) {
		this.hotPostId = hotPostId;
	}
	
	

}