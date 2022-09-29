//index 页面
$('#menu-toggle').on('click',function() {
    $('.m-item').toggleClass('m-mobile-hide');
});

//detail 页面
$('#toc-button').popup({
    popup:$('.toc-container.popup'),
    on:'click',
    position:'left center'
} );
$('#payButton').popup({
    popup:$('.payQR.popup'),
    on:'click',
    position:'bottom center'
} );

$('.weixin').popup({
    popup:$('.wechat-qr'),
    // on:'click',
    position:'left center'
} );

$('#toTop-button').click(function () {
$(window).scrollTo(0,500);
})


tocbot.init({
    // Where to render the table of contents.
    tocSelector: '.js-toc',
    // Where to grab the headings to build the table of contents.
    contentSelector: '.js-toc-content',
    // Which headings to grab inside of the contentSelector element.
    headingSelector: 'h1, h2, h3',
    // For headings inside relative or absolute positioned containers within content.
    hasInnerContainers: true,
});


var waypoint = new Waypoint({
    element: document.getElementById('waypoint'),
    handler: function(direction) {
        if (direction=='down'){
            $('#toolbar').show(500);
        }else {
            $('#toolbar').hide(500);
        }
        console.log('Scrolled to waypoint!' + direction);
    }
})

//about 页面
$('.qq').popup({
    popup:$('.qq-qr'),
    position:'bottom center'
});

$('.wechat').popup({
    popup:$('.wexin-qr'),
   // on:'click',
    position:'bottom center'
} );



