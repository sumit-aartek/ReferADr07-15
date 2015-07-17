$(function() {
  $('#fileupload').fileupload({
    dataType : 'json',
    done : function(e, data) {
      var s=",";
      $("tr:has(td)").remove();
      $("#uploaded-files").show();
      $.each(data.result, function(index, file) {
        $("#uploaded-files").append($('<tr/>')
          .append($('<td id=docId' + index + '/>').text(file.fileName))
          // form binding for pojo 
          //TODO :- change only path value with reference value.
          .append('<input type="text" style="display:none;" value="'+file.fileName+'"/>')
         .append($('<td/>').text(file.fileType)))
          s=s+","+file.fileName;
      });
      document.getElementById('imageId').value=s;
    }
  });
});