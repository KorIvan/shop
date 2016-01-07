<%@ include file="header.jsp" %>

<form method="post" >
<p>Date from: <input type="text" name="from"></p>
<p>Date to: <input type="text" name="to"></p>
<input type="submit" value="Get income"/>
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

</form>
<div class="container">
    <div class="row">
        <div class='col-sm-6'>
            <div class="form-group">
                <div class='input-group date' id='datetimepicker1'>
                    <input type='text' class="form-control" />
                    <span class="input-group-addon">
                        <span class="glyphicon glyphicon-calendar"></span>
                    </span>
                </div>
            </div>
        </div>

    </div>
</div>
<h2>Income</h2>
<p>${income}</p>
        <script type="text/javascript">
            $(function () {
                $('#datetimepicker1').datetimepicker();
            });
        </script>

<%@ include file="footer.jsp" %>