$(function() {
	  $('#patientNameJsp').fileupload({
    dataType : 'json',
    done : function(e, data) {
      var doc=",";
      $("tr:has(td)").remove();
      $("#patient-uploaded-files").show();
      $.each(data.result, function(index, file) {
        $("#patient-uploaded-files").append($('<tr/>')
          .append($('<td id=referDocId' + index + '/>').text(file.fileName))
          // form binding for pojo 
          //TODO :- change only path value with reference value.
          .append('<form:hidden style="display:none;" path="patientInfo.patientId" value="'+file.fileName+'"/>')
         .append($('<td/>').text(file.fileSize)))
         doc=doc+","+file.fileName;
      });
     document.getElementById('referDocName').value=doc;
    }
  });
});