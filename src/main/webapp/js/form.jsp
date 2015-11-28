<html>
  <head>
    <title>Firewall</title>
    <meta charset="utf8">
    <script type="text/javascript"></script> 
    <style>
      table
      {
        border: 1px solid #000; /* Рамка вокруг таблицы */
        empty-cells: hide;
        border-collapse: collapse;
        background: #e8edff; /* Цвет фона нечетных строк */
        margin:0;
      }
      th
      {
        text-align: center;
        background-color: silver;
        font-weight: bold;
      }
      td
      {
        text-align: center;
      }
      tr.dark
      {
        background: #dddddd; /* Цвет фона четных строк */
      }
      tr.light
      {
        background: #e8edff; /* Цвет фона четных строк */
      }
      input.right
      {
        float:right;
      }
    </style>

    <script>
      var checkall = document.getElementById("checkall"),
          checkboxes;
      var checkall0 = document.getElementById("checkall0");

      function chAll ()
      {
        checkboxes= document.querySelectorAll('input.check')
        for(var i=0; i<checkboxes.length; i++)
        {
          checkboxes[i].onclick = function()
          {
            var checkedCount = document.querySelectorAll('input.check:checked').length;        
            checkall.checked = checkedCount > 0 & checkedCount == checkboxes.length;
            checkall0.checked =checkall.checked;        		
    	  }
	}
      }

      //определяет текущую и предыдущую строки.
      function sendRowUp(CurrentRow,PreviousRow)
      {
   	PreviousRow.parentNode.insertBefore(CurrentRow,PreviousRow);
   	return false;
      }

      function rename(currentRowId)
      {
         // Меняем значения для Привиос строки
         document.getElementsByName("delete"+String(currentRowId))[0].setAttribute("name", "delete"+String(100000));
         document.getElementsByName("delete"+String(100000))[0].setAttribute("onclick", "delrow ("+String(currentRowId-1)+")");

         document.getElementsByName("up"+String(currentRowId))[0].setAttribute("name", "up"+String(100000));
         document.getElementsByName("up"+String(100000))[0].setAttribute("onclick", "moveRowUp("+String(currentRowId-1)+")");

         document.getElementsByName("down"+String(currentRowId))[0].setAttribute("name", "down"+String(100000));
         document.getElementsByName("down"+String(100000))[0].setAttribute("onclick", "moveRowDown("+String(currentRowId-1)+")");

         document.getElementsByName("addUp"+String(currentRowId))[0].setAttribute("name", "addUp"+String(100000));
         document.getElementsByName("addUp"+String(100000))[0].setAttribute("onclick", "addRowUp("+String(currentRowId-1)+")");
		
         // Меняем значения для Каррент строки
         document.getElementsByName("delete"+String(currentRowId-1))[0].setAttribute("name", "delete"+String(currentRowId));
         document.getElementsByName("delete"+String(currentRowId))[0].setAttribute("onclick", "delrow ("+String(currentRowId)+")");

         document.getElementsByName("up"+String(currentRowId-1))[0].setAttribute("name", "up"+String(currentRowId));
         document.getElementsByName("up"+String(currentRowId))[0].setAttribute("onclick", "moveRowUp("+String(currentRowId)+")");
		
         document.getElementsByName("down"+String(currentRowId-1))[0].setAttribute("name", "down"+String(currentRowId));
         document.getElementsByName("down"+String(currentRowId))[0].setAttribute("onclick", "moveRowDown("+String(currentRowId)+")");

         document.getElementsByName("addUp"+String(currentRowId-1))[0].setAttribute("name", "addUp"+String(currentRowId));
         document.getElementsByName("addUp"+String(currentRowId))[0].setAttribute("onclick", "addRowUp("+String(currentRowId)+")");

          //Восстанавливаем имена
         document.getElementsByName("delete"+String(100000))[0].setAttribute("name", "delete"+String(currentRowId-1));
         document.getElementsByName("up"+String(100000))[0].setAttribute("name", "up"+String(currentRowId-1));
         document.getElementsByName("down"+String(100000))[0].setAttribute("name", "down"+String(currentRowId-1));
         document.getElementsByName("addUp"+String(100000))[0].setAttribute("name", "addUp"+String(currentRowId-1));
      }

      function delrow (r)
      {
        var tbody = document.getElementsByTagName("tbody")[0]; // Получаем ссылку на tbody 
        document.getElementById('myTable').deleteRow(r);

    	if(tbody.rows.length==1 && checkall.checked)
        {
          checkall.checked=0;
          checkall0.checked =checkall.checked;
        }

        chAll(); 

        var checkedCount = document.querySelectorAll('input.check:checked').length;        
        checkall.checked = checkedCount > 0 & checkedCount == checkboxes.length;
        checkall0.checked =checkall.checked;         

    	for (var i = r; i < tbody.rows.length; i++) 
    	{
          var row =document.getElementById(i+1); // смена цвета строки
          if(row.id&1)
          {
             row.setAttribute("class", "dark");
          }
          else
          {
             row.setAttribute("class", "light");
          }

          document.getElementById(i+1).setAttribute("id",String(i))
          //Кнопка удаления
          document.getElementsByName("delete"+String(i+1))[0].setAttribute("name", "delete"+String(i));
          document.getElementsByName("delete"+String(i))[0].setAttribute("onclick", "delrow ("+String(i)+")");
          //Кнопка двигать вверх
          document.getElementsByName("up"+String(i+1))[0].setAttribute("name", "up"+String(i));
          document.getElementsByName("up"+String(i))[0].setAttribute("onclick", "moveRowUp("+String(i)+")");
          //Кнопка двигать вниз
          document.getElementsByName("down"+String(i+1))[0].setAttribute("name", "down"+String(i));
          document.getElementsByName("down"+String(i))[0].setAttribute("onclick", "moveRowDown("+String(i)+")");
          //Кнопка добавления строки вверх
          document.getElementsByName("addUp"+String(i+1))[0].setAttribute("name", "addUp"+String(i));
          document.getElementsByName("addUp"+String(i))[0].setAttribute("onclick", "addRowUp("+String(i)+")");
    	}	
      }

      //меняем местами строки
      function moveRowUp(val)
      {
   	var currentRowId=val;
        var row =document.getElementById(currentRowId); // смена цвета строки
        var row1 =document.getElementById(currentRowId-1); // смена цвета строки
        if(row.id&1)
        {
          row.setAttribute("class", "dark");
          row1.setAttribute("class", "light");
        }
        else
        {
          row.setAttribute("class", "light");
          row1.setAttribute("class", "dark");
        }

   	var table=document.getElementById("myTable");
   	for (i=0;i<table.rows.length;i++)
   	{
          if (table.rows[i].id==currentRowId) 
          {
            if (i == 1) 
            {
              return;
            }
            else
            {
              CurrentRow = table.rows[i];
              PreviousRow = table.rows[i - 1];
            }
            sendRowUp(CurrentRow,PreviousRow);
            table.rows[i-1].id=currentRowId-1;
            table.rows[i].id=currentRowId;
          
            rename(currentRowId);            
            break;
          }
   	}
   	return false;
      }

      function moveRowDown(val)
      {	
        var currentRowId=val;

        var row =document.getElementById(currentRowId); // смена цвета строки
        var row1 =document.getElementById(currentRowId+1); // смена цвета строки
        if(row.id&1)
        {
          row.setAttribute("class", "dark");
          row1.setAttribute("class", "light");
        }
        else
        {
          row.setAttribute("class", "light");
          row1.setAttribute("class", "dark");
        }

        var table=document.getElementById("myTable");
        for (i=0;i<table.rows.length;i++)
        {
          if (table.rows[i].id==currentRowId) 
          {
            if (i == table.rows.length-1)
            {
              return;
            }
            else
            {
              CurrentRow = table.rows[i+1];
              PreviousRow = table.rows[i];
            }
            sendRowUp(CurrentRow,PreviousRow);
            currentRowId=currentRowId+1;
            table.rows[i+1].id=currentRowId;
            table.rows[i].id=currentRowId-1;
            rename(currentRowId);
            break;
          }
   	}
   	return false;
      }

      // Добавление строки наверх
      function addRowUp(r)
      {
        addrow ();
        var table=document.getElementById("myTable");
        for (i=table.rows.length-1; i>r ;i--)
        {
          moveRowUp(i);
        }
      }

      function addrow ()
      {
        var tbody = document.getElementsByTagName("tbody")[0]; // Получаем ссылку на tbody 
        var row = tbody.insertRow(tbody.rows.length); // Добавляем строку
        row.id = tbody.rows.length-1;
        if(!(row.id&1))
        {
             row.setAttribute("class", "dark");
        }
        else
        {
             row.setAttribute("class", "light");
        }	
        var cell0 = row.insertCell(0); //Chain
        var cell1 = row.insertCell(1); //Action
        var cell2 = row.insertCell(2); //Condition
        var cell3 = row.insertCell(3); // Move
        var cell4 = row.insertCell(4); //Add
 
        // Формируем строку элементов управления
        var check = document.createElement("input") // checkbox
        check.setAttribute("type", "checkbox");
        check.setAttribute("name", "check"+String(row.id));
        check.setAttribute("id", "0"+String(row.id));
        check.setAttribute("class", "check");
        check.setAttribute("value", "test");
        cell0.appendChild(check);

        var checkall = document.getElementById("checkall");
    	if(checkall.checked)
        {
          check.checked=1;
          checkall0.checked =checkall.checked;          		
    	}
        chAll();       

        var del = document.createElement("input"); // deleteRow
        del.setAttribute("type", "image");
        del.setAttribute("name", "delete"+String(row.id));
        del.setAttribute("onclick", "delrow("+row.id+")");
        del.setAttribute("src", "img/usun.png");
        cell0.appendChild(del);
 
        var textin0 = document.createElement("input"); // Chain
        textin0.setAttribute("type", "text");
        cell0.appendChild(textin0);
       	
        var textin1 = document.createElement("input"); //Action
        textin1.setAttribute("type", "text");
        cell1.appendChild(textin1);

        var textin2 = document.createElement("input"); //Condition
        textin2.setAttribute("type", "text");
        cell2.appendChild(textin2);

        var mvUp = document.createElement("input"); //Move up
        mvUp.setAttribute("type", "image");
        mvUp.setAttribute("name", "up"+String(row.id));
        mvUp.setAttribute("onclick", "moveRowUp("+row.id+")");
        mvUp.setAttribute("src", "img/up.gif"); //picture change
        cell3.appendChild(mvUp);

        var mvDown = document.createElement("input"); //Move down
        mvDown.setAttribute("type", "image");
        mvDown.setAttribute("name", "down"+String(row.id));
        mvDown.setAttribute("onclick", "moveRowDown("+row.id+")");
        mvDown.setAttribute("src", "img/down.gif"); //picture change
        cell3.appendChild(mvDown);

        var addUp = document.createElement("input"); //Add up
        addUp.setAttribute("type", "image");
        addUp.setAttribute("name", "addUp"+String(row.id));
        addUp.setAttribute("onclick", "addRowUp("+row.id+")");
        addUp.setAttribute("src", "img/before.gif"); //picture change
        cell4.appendChild(addUp);

        tbody.appendChild(row);
     }
    </script>
    </head>
    <body background="img/bg.jpg">
      Select all <input type="checkbox" id="checkall">
      <table  align="center" id="myTable" width=100%>
        <tbody>
	  <tr id="0"> 
	    <th>Chain </th>
            <th>Action</th>	    
	    <th>Condition</th>
	    <th>Move</th>
	    <th>Add</th>
	  </tr>
        </tbody>
      </table><br>
      <div align="left">
        Select all <input type="checkbox" id="checkall0">
        <input type="submit" value="Apply selected"/>
        <input type="button"  name ="delSel" id="delSel" value="Delete selected" onclick="delSel()"/>
        <input type="button" name="add" id="Add rule" class ="right" value="Add rule" onclick="addrow ()" />
      </div><br></br>
      <div align="left">
        Global action:<select name="Action">
        <option selected="selected" value="r0">accept</option>
        <option value="r1">drop</option>
        </select>
        <input type="submit" value="Reset firewall"/>
      </div>
    <script>
      var checkall = document.getElementById("checkall"),
          checkall0 = document.getElementById("checkall0");
      checkall.onclick = function() 
      {
        var checkboxes = document.querySelectorAll('input.check');
        for(var i=0; i<checkboxes.length; i++)
        {
    	  checkboxes[i].onclick = function() 
          {
            var checkedCount = document.querySelectorAll('input.check:checked').length;        
            checkall.checked = checkedCount > 0 & checkedCount == checkboxes.length;
            checkall0.checked = checkall.checked;  
          }
        }
        if(checkall.checked)
        {
          checkall0.checked = 1;
        }
        else
        {
          checkall0.checked = 0;
        }
        for(var i=0; i<checkboxes.length; i++)
        {
    	  checkboxes[i].checked = this.checked;
        }
      }

      checkall0.onclick = function() 
      {
        var checkboxes = document.querySelectorAll('input.check');
        for(var i=0; i<checkboxes.length; i++)
        {
    	  checkboxes[i].onclick = function() 
          {
            var checkedCount = document.querySelectorAll('input.check:checked').length;        
            checkall0.checked = checkedCount > 0 & checkedCount == checkboxes.length;
            checkall.checked = checkall0.checked;  		
          }
        }

        if(checkall0.checked)
        {
          checkall.checked = 1;
        }
        else
        {
          checkall.checked = 0;
        }
        for(var i=0; i<checkboxes.length; i++)
        {
    	  checkboxes[i].checked = this.checked;
        }

      }

      var delSel = document.getElementById("delSel")
      delSel.onclick=function()
      {
        var checkboxes = document.querySelectorAll('input.check:checked');
        for(var i=0; i<checkboxes.length; i++)
        {
          var row =checkboxes[i].id;
    	  delrow(row-i);
        }
      }
    </script> 
  </body>

</html>