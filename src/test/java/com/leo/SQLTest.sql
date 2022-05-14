# 员工登录是信息获取及验证身5份
select name, passwd, dep_id, dep_name, manager_id
from employee,
     department
where employee.id = 8001
  and employee.dep_id = department.id;

# 查询部门信息, 部门编号, 部门名称, 部门主管编号, 部门主管姓名
select d.id, d.dep_name, e.id, e.name
from department as d
         join employee as e
              on d.manager_id = e.id;

# 查询用户信息
select e.id,
       e.name,
       e.sex,
       e.rate,
       e.address,
       e.birth,
       e.cardId,
       d.dep_name,
       job.job_name
from employee as e,
     department as d,
     job
where e.name = '范明俊'
  and e.dep_id = d.id
  and e.job_id = job.id;

# 查询工资
select round(job.salary * (1 + e.rate), 2)
from job,
     employee as e
where e.id = 8001
  and e.job_id = job.id;

update employee
set passwd= '123456'
where id = 8001;

update department
set manager_id = 8012
where id = 3;