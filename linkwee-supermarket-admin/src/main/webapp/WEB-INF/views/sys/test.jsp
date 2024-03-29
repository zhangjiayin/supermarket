<%@ page language="java" pageEncoding="utf-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!-- DataTables -->
<script type="text/javascript" src="assets/plugins/data-tables/js/jquery.dataTables.min.js"  ></script>
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/jquery.dataTables.min.css"  />
<link rel="stylesheet" type="text/css" href="assets/plugins/data-tables/css/dataTables.bootstrap.min.css"  />
<script type="text/javascript">
$(document).ready(function (){
	var ajaxData = [
			            [
			               "1",
			               "Tiger Nixon",
			               "System Architect",
			               "Edinburgh",
			               "5421",
			               "2011/04/25",
			               "$320,800"
			            ],
			            [
			               "2",
			               "Garrett Winters",
			               "Accountant",
			               "Tokyo",
			               "8422",
			               "2011/07/25",
			               "$170,750"
			            ],
			            [
			               "3",
			               "Ashton Cox",
			               "Junior Technical Author",
			               "San Francisco",
			               "1562",
			               "2009/01/12",
			               "$86,000"
			            ],
			            [
			               "4",
			               "Cedric Kelly",
			               "Senior Javascript Developer",
			               "Edinburgh",
			               "6224",
			               "2012/03/29",
			               "$433,060"
			            ],
			            [
			               "5",
			               "Airi Satou",
			               "Accountant",
			               "Tokyo",
			               "5407",
			               "2008/11/28",
			               "$162,700"
			            ],
			            [
			               "6",
			               "Brielle Williamson",
			               "Integration Specialist",
			               "New York",
			               "4804",
			               "2012/12/02",
			               "$372,000"
			            ],
			            [
			               "7",
			               "Herrod Chandler",
			               "Sales Assistant",
			               "San Francisco",
			               "9608",
			               "2012/08/06",
			               "$137,500"
			            ],
			            [
			               "8",
			               "Rhona Davidson",
			               "Integration Specialist",
			               "Tokyo",
			               "6200",
			               "2010/10/14",
			               "$327,900"
			            ],
			            [
			               "9",
			               "Colleen Hurst",
			               "Javascript Developer",
			               "San Francisco",
			               "2360",
			               "2009/09/15",
			               "$205,500"
			            ],
			            [
			               "10",
			               "Sonya Frost",
			               "Software Engineer",
			               "Edinburgh",
			               "1667",
			               "2008/12/13",
			               "$103,600"
			            ],
			            [
			               "11",
			               "Jena Gaines",
			               "Office Manager",
			               "London",
			               "3814",
			               "2008/12/19",
			               "$90,560"
			            ],
			            [
			               "12",
			               "Quinn Flynn",
			               "Support Lead",
			               "Edinburgh",
			               "9497",
			               "2013/03/03",
			               "$342,000"
			            ],
			            [
			               "13",
			               "Charde Marshall",
			               "Regional Director",
			               "San Francisco",
			               "6741",
			               "2008/10/16",
			               "$470,600"
			            ],
			            [
			               "14",
			               "Haley Kennedy",
			               "Senior Marketing Designer",
			               "London",
			               "3597",
			               "2012/12/18",
			               "$313,500"
			            ],
			            [
			               "15",
			               "Tatyana Fitzpatrick",
			               "Regional Director",
			               "London",
			               "1965",
			               "2010/03/17",
			               "$385,750"
			            ],
			            [
			               "16",
			               "Michael Silva",
			               "Marketing Designer",
			               "London",
			               "1581",
			               "2012/11/27",
			               "$198,500"
			            ],
			            [
			               "17",
			               "Paul Byrd",
			               "Chief Financial Officer (CFO)",
			               "New York",
			               "3059",
			               "2010/06/09",
			               "$725,000"
			            ],
			            [
			               "18",
			               "Gloria Little",
			               "Systems Administrator",
			               "New York",
			               "1721",
			               "2009/04/10",
			               "$237,500"
			            ],
			            [
			               "19",
			               "Bradley Greer",
			               "Software Engineer",
			               "London",
			               "2558",
			               "2012/10/13",
			               "$132,000"
			            ],
			            [
			               "20",
			               "Dai Rios",
			               "Personnel Lead",
			               "Edinburgh",
			               "2290",
			               "2012/09/26",
			               "$217,500"
			            ],
			            [
			               "21",
			               "Jenette Caldwell",
			               "Development Lead",
			               "New York",
			               "1937",
			               "2011/09/03",
			               "$345,000"
			            ],
			            [
			               "22",
			               "Yuri Berry",
			               "Chief Marketing Officer (CMO)",
			               "New York",
			               "6154",
			               "2009/06/25",
			               "$675,000"
			            ],
			            [
			               "23",
			               "Caesar Vance",
			               "Pre-Sales Support",
			               "New York",
			               "8330",
			               "2011/12/12",
			               "$106,450"
			            ],
			            [
			               "24",
			               "Doris Wilder",
			               "Sales Assistant",
			               "Sidney",
			               "3023",
			               "2010/09/20",
			               "$85,600"
			            ],
			            [
			               "25",
			               "Angelica Ramos",
			               "Chief Executive Officer (CEO)",
			               "London",
			               "5797",
			               "2009/10/09",
			               "$1,200,000"
			            ],
			            [
			               "26",
			               "Gavin Joyce",
			               "Developer",
			               "Edinburgh",
			               "8822",
			               "2010/12/22",
			               "$92,575"
			            ],
			            [
			               "27",
			               "Jennifer Chang",
			               "Regional Director",
			               "Singapore",
			               "9239",
			               "2010/11/14",
			               "$357,650"
			            ],
			            [
			               "28",
			               "Brenden Wagner",
			               "Software Engineer",
			               "San Francisco",
			               "1314",
			               "2011/06/07",
			               "$206,850"
			            ],
			            [
			               "29",
			               "Fiona Green",
			               "Chief Operating Officer (COO)",
			               "San Francisco",
			               "2947",
			               "2010/03/11",
			               "$850,000"
			            ],
			            [
			               "30",
			               "Shou Itou",
			               "Regional Marketing",
			               "Tokyo",
			               "8899",
			               "2011/08/14",
			               "$163,000"
			            ],
			            [
			               "31",
			               "Michelle House",
			               "Integration Specialist",
			               "Sidney",
			               "2769",
			               "2011/06/02",
			               "$95,400"
			            ],
			            [
			               "32",
			               "Suki Burks",
			               "Developer",
			               "London",
			               "6832",
			               "2009/10/22",
			               "$114,500"
			            ],
			            [
			               "33",
			               "Prescott Bartlett",
			               "Technical Author",
			               "London",
			               "3606",
			               "2011/05/07",
			               "$145,000"
			            ],
			            [
			               "34",
			               "Gavin Cortez",
			               "Team Leader",
			               "San Francisco",
			               "2860",
			               "2008/10/26",
			               "$235,500"
			            ],
			            [
			               "35",
			               "Martena Mccray",
			               "Post-Sales support",
			               "Edinburgh",
			               "8240",
			               "2011/03/09",
			               "$324,050"
			            ],
			            [
			               "36",
			               "Unity Butler",
			               "Marketing Designer",
			               "San Francisco",
			               "5384",
			               "2009/12/09",
			               "$85,675"
			            ],
			            [
			               "37",
			               "Howard Hatfield",
			               "Office Manager",
			               "San Francisco",
			               "7031",
			               "2008/12/16",
			               "$164,500"
			            ],
			            [
			               "38",
			               "Hope Fuentes",
			               "Secretary",
			               "San Francisco",
			               "6318",
			               "2010/02/12",
			               "$109,850"
			            ],
			            [
			               "39",
			               "Vivian Harrell",
			               "Financial Controller",
			               "San Francisco",
			               "9422",
			               "2009/02/14",
			               "$452,500"
			            ],
			            [
			               "40",
			               "Timothy Mooney",
			               "Office Manager",
			               "London",
			               "7580",
			               "2008/12/11",
			               "$136,200"
			            ],
			            [
			               "41",
			               "Jackson Bradshaw",
			               "Director",
			               "New York",
			               "1042",
			               "2008/09/26",
			               "$645,750"
			            ],
			            [
			               "42",
			               "Olivia Liang",
			               "Support Engineer",
			               "Singapore",
			               "2120",
			               "2011/02/03",
			               "$234,500"
			            ],
			            [
			               "43",
			               "Bruno Nash",
			               "Software Engineer",
			               "London",
			               "6222",
			               "2011/05/03",
			               "$163,500"
			            ],
			            [
			               "44",
			               "Sakura Yamamoto",
			               "Support Engineer",
			               "Tokyo",
			               "9383",
			               "2009/08/19",
			               "$139,575"
			            ],
			            [
			               "45",
			               "Thor Walton",
			               "Developer",
			               "New York",
			               "8327",
			               "2013/08/11",
			               "$98,540"
			            ],
			            [
			               "46",
			               "Finn Camacho",
			               "Support Engineer",
			               "San Francisco",
			               "2927",
			               "2009/07/07",
			               "$87,500"
			            ],
			            [
			               "47",
			               "Serge Baldwin",
			               "Data Coordinator",
			               "Singapore",
			               "8352",
			               "2012/04/09",
			               "$138,575"
			            ],
			            [
			               "48",
			               "Zenaida Frank",
			               "Software Engineer",
			               "New York",
			               "7439",
			               "2010/01/04",
			               "$125,250"
			            ],
			            [
			               "49",
			               "Zorita Serrano",
			               "Software Engineer",
			               "San Francisco",
			               "4389",
			               "2012/06/01",
			               "$115,000"
			            ],
			            [
			               "50",
			               "Jennifer Acosta",
			               "Junior Javascript Developer",
			               "Edinburgh",
			               "3431",
			               "2013/02/01",
			               "$75,650"
			            ],
			            [
			               "51",
			               "Cara Stevens",
			               "Sales Assistant",
			               "New York",
			               "3990",
			               "2011/12/06",
			               "$145,600"
			            ],
			            [
			               "52",
			               "Hermione Butler",
			               "Regional Director",
			               "London",
			               "1016",
			               "2011/03/21",
			               "$356,250"
			            ],
			            [
			               "53",
			               "Lael Greer",
			               "Systems Administrator",
			               "London",
			               "6733",
			               "2009/02/27",
			               "$103,500"
			            ],
			            [
			               "54",
			               "Jonas Alexander",
			               "Developer",
			               "San Francisco",
			               "8196",
			               "2010/07/14",
			               "$86,500"
			            ],
			            [
			               "55",
			               "Shad Decker",
			               "Regional Director",
			               "Edinburgh",
			               "6373",
			               "2008/11/13",
			               "$183,000"
			            ],
			            [
			               "56",
			               "Michael Bruce",
			               "Javascript Developer",
			               "Singapore",
			               "5384",
			               "2011/06/27",
			               "$183,000"
			            ],
			            [
			               "57",
			               "Donna Snider",
			               "Customer Support",
			               "New York",
			               "4226",
			               "2011/01/25",
			               "$112,000"
			            ]
			         ];
	   var table = $('#example').DataTable({
	      data: ajaxData,
	      'columnDefs': [{
	         'targets': 0,
	         'searchable': false,
	         'orderable': false,
	         'className': 'dt-body-center',
	         'render': function (data, type, full, meta){
	             return '<input type="checkbox" name="id[]" value="' + $('<div/>').text(data).html() + '">';
	         }
	      }],
	      'order': [[1, 'asc']]
	   });

	   // Handle click on "Select all" control
	   $('#example-select-all').on('click', function(){
	      // Get all rows with search applied
	      var rows = table.rows({ 'search': 'applied' }).nodes();
	      // Check/uncheck checkboxes for all rows in the table
	      $('input[type="checkbox"]', rows).prop('checked', this.checked);
	   });

	   // Handle click on checkbox to set state of "Select all" control
	   $('#example tbody').on('change', 'input[type="checkbox"]', function(){
	      // If checkbox is not checked
	      if(!this.checked){
	         var el = $('#example-select-all').get(0);
	         // If "Select all" control is checked and has 'indeterminate' property
	         if(el && el.checked && ('indeterminate' in el)){
	            // Set visual state of "Select all" control 
	            // as 'indeterminate'
	            el.indeterminate = true;
	         }
	      }
	   });

	   // Handle form submission event
	   $('#frm-example').on('submit', function(e){
		   var form = this;

	      // Iterate over all checkboxes in the table
	      table.$('input[type="checkbox"]').each(function(){
	         // If checkbox doesn't exist in DOM
	         if(!$.contains(document, this)){
	            // If checkbox is checked
	            if(this.checked){
	               // Create a hidden element 
	               $(form).append(
	                  $('<input>')
	                     .attr('type', 'hidden')
	                     .attr('name', this.name)
	                     .val(this.value)
	               );
	            }
	         } 
	      });

	      // FOR DEMONSTRATION ONLY     
	      
	      // Output form data to a console     
	      $('#example-console').text($(form).serialize());
	      console.log("Form submission", $(form).serialize());
	       
	      // Remove added elements
	      $('input[type="hidden"][name="id\[\]"]', form).remove();     

	      // Prevent actual form submission
	      e.preventDefault();
	   });

	});
</script>
<form name="frm-example" id="frm-example">
<table id="example" class="display select" cellspacing="0" width="100%">
   <thead>
      <tr>
         <th><input type="checkbox" name="select_all" value="1" id="example-select-all"></th>
         <th>Name</th>
         <th>Position</th>
         <th>Office</th>
         <th>Extn.</th>
         <th>Start date</th>
         <th>Salary</th>
      </tr>
   </thead>
   <tfoot>
      <tr>
         <th></th>
         <th>Name</th>
         <th>Position</th>
         <th>Office</th>
         <th>Extn.</th>
         <th>Start date</th>
         <th>Salary</th>
      </tr>
   </tfoot>
</table>
<p class="form-group">
   <button type="submit" class="btn btn-primary">Submit</button>
</p>
<p class="form-group">
   <b>Data submitted to the server:</b>
   <pre id="example-console"></pre>
</p>
</form>
</div>

