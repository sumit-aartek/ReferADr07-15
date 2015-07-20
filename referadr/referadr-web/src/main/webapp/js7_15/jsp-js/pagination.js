/**
 * @aartek for pagination through script
 */
$(document)
			.ready(
					function() {
						// number of records per page
						var pageSize = 8;
						// reset current page counter on load
						$("#hdnActivePage").val(1);
						// calculate number of pages
						var numberOfPages = $('table tr').length / pageSize;
						numberOfPages = numberOfPages.toFixed();
						// action on 'next' click
						
						 if (numberOfPages == 1) {
        	  $("a.go-ahead").hide();
              $("a.go-ahead span").hide();
        }
        // action on 'next' click
             	var currentPage = Number($("#hdnActivePage").val());
            $("#hdnActivePage").val(currentPage);
            // first hide all rows
            $("table#testTable tbody tr").hide();
        	// show only the necessary rows based upon activePage and Pagesize
            $("table#testTable tbody tr:nth-child(-n+" + ($("#hdnActivePage").val() * pageSize) + ")").show();
            var currentPage = Number($("#hdnActivePage").val());
						
						
						
						
	$('.pagination a.go-ahead').click(function() {
	// show only the necessary rows based upon activePage and Pagesize
	$("table#testTable tbody tr:nth-child(-n+"+ (($("#hdnActivePage").val() * pageSize) + pageSize)+ ")").show();
	$("table#testTable tbody tr:nth-child(-n+"+ $("#hdnActivePage").val()* pageSize + ")").hide();
	var currentPage = Number($("#hdnActivePage").val());
	// update activepage
    $("#hdnActivePage").val(Number($("#hdnActivePage").val()) + 1);
	// check if previous page button is necessary (not on first page)
				if ($("#hdnActivePage").val() != "1") {
				$("a.go-previous").show();
				$("a.go-previous span").show();
				}
	// check if next page button is necessary (not on last page)
		        if ($("#hdnActivePage").val() == numberOfPages) {
			    $("a.go-ahead").hide();
				$("a.go-ahead span").hide();
				}
    });

    $('.pagination a.go-previous').click(function() {var currentPage = Number($("#hdnActivePage").val());
			    $("#hdnActivePage").val(currentPage - 1);
	// first hide all rows
				$("table#testTable tbody tr").hide();
	// and only turn on visibility on necessary rows
	$("table#testTable tbody tr:nth-child(-n+"+ ($("#hdnActivePage").val() * pageSize)+ ")").show();
	            $("table#testTable tbody tr:nth-child(-n+"+ (($("#hdnActivePage").val() * pageSize) - pageSize)+ ")").hide();
											// check if previous button is necessary (not on first page)
											if ($("#hdnActivePage").val() == "1") {
												$("a.go-previous").hide();
												$("a.go-previous span").hide();
											}
											// check if next button is necessary (not on last page)
											if ($("#hdnActivePage").val() < numberOfPages) {
												$("a.go-ahead").show();
												$("a.go-ahead span").show();
											}
											if ($("#hdnActivePage").val() == 1) {
												$(".pagination span").hide();
											}
										});

					});