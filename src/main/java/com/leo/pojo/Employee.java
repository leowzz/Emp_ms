package com.leo.pojo;

public class Employee extends People {
    private Integer id; // 员工编号
    private String name; // 员工姓名
    private String passwd; //员工密码
    private String age; //员工年龄
    private Float rate; // 奖金率
    private Float salary; // 奖金率
    private String sex; // true为男性, false为女性F
    private String cardId; // 身份证号
    private String birth; //出生日期
    private String address; // 籍贯
    private String tel; // 电话
    private Integer dep_id; // 部门
    private Integer job_id; //职位
    private String dep_name; // 部门名称
    private String job_name; // 职位名称
    private Integer manager_id = null; //部门主管类型
    
    public Employee() {
    }
    
    public Employee(String name, String passwd, String sex, String cardId, String birth,
                    String address, String tel, int dep_id, int job_id) {
        this.name = name;
        this.passwd = passwd;
        this.sex = sex;
        this.cardId = cardId;
        this.birth = birth;
        this.address = address;
        this.tel = tel;
        this.dep_id = dep_id;
        this.job_id = job_id;
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public void setName(String name) {
        this.name = name;
    }
    
    public String getPasswd() {
        return passwd;
    }
    
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    
    public String getAge() {
        return age;
    }
    
    public void setAge(String age) {
        this.age = age;
    }
    
    public Float getRate() {
        return rate;
    }
    
    public void setRate(float rate) {
        this.rate = rate;
    }
    
    public String getSex() {
        return sex;
    }
    
    public void setSex(String sex) {
        this.sex = sex;
    }
    
    public String getCardId() {
        return cardId;
    }
    
    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
    
    public String getBirth() {
        return birth;
    }
    
    public void setBirth(String birth) {
        this.birth = birth;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getTel() {
        return tel;
    }
    
    public void setTel(String tel) {
        this.tel = tel;
    }
    
    public int getDep_id() {
        return dep_id;
    }
    
    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }
    
    public int getJob_id() {
        return job_id;
    }
    
    public void setJob_id(int job_id) {
        this.job_id = job_id;
    }
    
    public String getDep_name() {
        return dep_name;
    }
    
    public void setDep_name(String dep_name) {
        this.dep_name = dep_name;
    }
    
    public String getJob_name() {
        return job_name;
    }
    
    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }
    
    public Float getSalary() {
        return salary;
    }
    
    public void setSalary(Float salary) {
        this.salary = salary;
    }
    
    public Integer getManager_id() {
        return manager_id;
    }
    
    public void setManager_id(Integer manager_id) {
        this.manager_id = manager_id;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", passwd='" + passwd + '\'' +
                ", age='" + age + '\'' +
                ", rate=" + rate +
                ", salary=" + salary +
                ", sex='" + sex + '\'' +
                ", cardId='" + cardId + '\'' +
                ", birth='" + birth + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", dep_id=" + dep_id +
                ", job_id=" + job_id +
                ", dep_name='" + dep_name + '\'' +
                ", job_name='" + job_name + '\'' +
                ", manager_id='" + manager_id + '\'' +
                '}';
    }
}
