<#import "../parts/common.ftlh" as c>
<@c.page>


        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/employees/employeeEdit">
                    <input type="hidden" name="employeeId" value="${employee.id}"/>
                    <div class="form-group">
                        <input type="text" class="form-control" name="firstName" placeholder="Enter First Name" value="${employee.firstName}">
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="lastName" placeholder="Enter Last Name" value="${employee.lastName}">
                    </div>

                    <div class="form-group">
                        <input type="text" class="form-control" name="salary" placeholder="Enter salary" value="${employee.salary}">
                    </div>

                    <div class="form-group">
                        Birthday
                        <input type="date" class="form-control" name="date" value="${employee.getBirthForFormFormat()}">
                    </div>

                    <div class="form-group">
                    <select class="form-control" id="exampleFormControlSelect1" name="idDepartment">
                        <option value="${employee.idDepartment.id}">${employee.idDepartment.name}</option>
                    <#list departments as department>
                        <option value="${department.id}">${department.name}</option>
                    </#list>
                    </select>
                    </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Edit</button>
                </div>
            </form>
        </div>
</@c.page>