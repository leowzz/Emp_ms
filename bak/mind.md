## 登录页面

带管理员登录入口

-   管理员登录
    -   select username, passwd ... ✔

登录时通过用户输入的员工号和密码查询用户信息进行登录

-   select id,name ... ✔

登录通过用户id和部门主管id判断跳转页面

## 员工页面

首页展示个人信息 

​		获取session内的个人信息进行展示

-   显示详细信息按钮
    -   查询详细信息
        -   select ... ✔

-   个人信息修改按钮
    -   提交按钮->insert into, 跳转回个人页面

侧栏

-   查询薪资
    -   计算薪资进行展示
        -   select
-   修改密码
    -   update

## 部门主管页面

首页展示员工信息

​		select ... where ... e.dep_id=self.dep_id✔

-   添加员工
    -   insert into ... ✔
-   查询员工
    -   通过姓名查询
        -   selectInfoByName✔
    -   通过工号查询
        -   selectInfoById✔
-   修改员工
    -   update
-   删除员工
    -   delete

侧栏

-   个人信息

    -   获取session内的个人信息进行展示

    -   显示详细信息按钮

        -   查询详细信息
            -   select

        -   个人信息修改按钮
            -   提交按钮->insert into, 跳转回个人页面

-   薪资管理

    -   显示员工信息(展示薪资, 奖金率) 
    -   修改按钮

-   修改密码

    -   update

## 管理员页面

首页展示各个部门详细信息 

-   selectAllDepInfo✔

-   部门信息后面 修改部门主管按钮
    -   update

侧栏

-   修改密码
    -   update