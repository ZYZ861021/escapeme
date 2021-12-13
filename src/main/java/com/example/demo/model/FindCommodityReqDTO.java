package com.example.demo.model;

import lombok.Data;

@Data
public class FindCommodityReqDTO {

	private Integer star;

	private Integer starTop;

    private Integer type;

    private String title;
    
    
    public FindCommodityReqDTO() {
	}

    public FindCommodityReqDTO(Integer star, Integer starTop, Integer type, String title) {
		this.star = star;
		this.starTop = starTop;
		this.type = type;
		this.title = title;
	}
    
    public Integer getStar() {
		return star;
	}

	public Integer getStarTop() {
		return starTop;
	}

	public Integer getType() {
		return type;
	}

	public String getTitle() {
		return title;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	public void setStarTop(Integer starTop) {
		this.starTop = starTop;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	@Override
	public String toString() {
		return "FindCommodityReqDTO [star=" + star + ", starTop=" + starTop + ", type=" + type + ", title=" + title
				+ "]";
	}
}
