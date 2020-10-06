<#import "../parts/common.ftlh" as c>
<@c.page>


        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data" action="/departments/departmentEdit">
                <div class="form-group">
                     <input type="hidden" name="departmentId" value="${department.id}"/>
                    <input type="text" class="form-control" name="departmentName" placeholder="Enter Department name" value="${department.name}">
                </div>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Edit</button>
                </div>
            </form>
        </div>
</@c.page>