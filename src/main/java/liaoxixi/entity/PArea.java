package liaoxixi.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PArea {
    public Integer id;
    public Double area;
    @JsonProperty("constantType")
    public Integer constant_type;
    public String dept_code;
    public String multipolygon;
    public String name;
    public String parent_code;
    public String qb_type;
    public String status;
    public Double xmax;
    public Double xmin;
    public Double ymax;
    public Double ymin;
    public String shape;

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConstant_type() {
        return constant_type;
    }

    public void setConstant_type(Integer constant_type) {
        this.constant_type = constant_type;
    }

    public String getDept_code() {
        return dept_code;
    }

    public void setDept_code(String dept_code) {
        this.dept_code = dept_code;
    }

    public String getMultipolygon() {
        return multipolygon;
    }

    public void setMultipolygon(String multipolygon) {
        this.multipolygon = multipolygon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public String getQb_type() {
        return qb_type;
    }

    public void setQb_type(String qb_type) {
        this.qb_type = qb_type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getXmax() {
        return xmax;
    }

    public void setXmax(Double xmax) {
        this.xmax = xmax;
    }

    public Double getXmin() {
        return xmin;
    }

    public void setXmin(Double xmin) {
        this.xmin = xmin;
    }

    public Double getYmax() {
        return ymax;
    }

    public void setYmax(Double ymax) {
        this.ymax = ymax;
    }

    public Double getYmin() {
        return ymin;
    }

    public void setYmin(Double ymin) {
        this.ymin = ymin;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
