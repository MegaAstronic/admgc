package net.moonj.admgc.genecode.project_info.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author admgc
 * @since 2018-10-14
 */
public class ProjectInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "proid", type = IdType.AUTO)
    private Integer proid;

    private String name;

    private String content;

    private String teachers;

    private String partner;

    private String period;

    private Double price;

    /**
     * 'primary;junior-high;senior-high'
     */
    private String groupName;

    /**
     * 总人数
     */
    private Integer place;

    /**
     * '例如：计算机类、农业类'
     */
    private String type;

    public Integer getProid() {
        return proid;
    }

    public void setProid(Integer proid) {
        this.proid = proid;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getTeachers() {
        return teachers;
    }

    public void setTeachers(String teachers) {
        this.teachers = teachers;
    }
    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ProjectInfo{" +
        "proid=" + proid +
        ", name=" + name +
        ", content=" + content +
        ", teachers=" + teachers +
        ", partner=" + partner +
        ", period=" + period +
        ", price=" + price +
        ", groupName=" + groupName +
        ", place=" + place +
        ", type=" + type +
        "}";
    }
}
