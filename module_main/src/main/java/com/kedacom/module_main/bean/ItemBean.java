package com.kedacom.module_main.bean;

/**
 * <pre>
 *     作者：Caowj
 *     邮箱：caoweijian@kedacom.com
 *     日期：2020/10/23 15:41
 * </pre>
 */

public class ItemBean {
    private String name;
    private Class className;

    public ItemBean(String name, Class className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getClassName() {
        return className;
    }

    public void setClassName(Class className) {
        this.className = className;
    }
}
