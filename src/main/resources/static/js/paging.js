$(function() {
  var pageNumber = 0;
  function draw() {
    $('#page_number').html(pageNumber + 1);
    $('tr').hide();
    $('tr:first,tr:gt(' + pageNumber * 10 + '):lt(10)').show();
  }
  $('#previous').click(function() {
    if (pageNumber > 0) {
      pageNumber--;
      draw();
    }
  });
  $('#next').click(function() {
    if (pageNumber < ($('tr').size() - 1) / 10 - 1) {
      pageNumber++;
      draw();
    }
  });
  draw();
});