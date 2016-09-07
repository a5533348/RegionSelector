package cn.xdeveloper.regionselector;

/**
 * 地区
 * Created by Laiyimin on 2016/9/2.
 */
public class RegionModel {

    private Long id;
    private Long pid;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
