/**
 * Resize function without multiple trigger
 *
 * Usage:
 * $(window).smartresize(function(){  
 *     // code here
 * });
 */
(function ($, sr) {
    // debouncing function from John Hann
    // http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
    var debounce = function (func, threshold, execAsap) {
        var timeout;

        return function debounced() {
            var obj = this, args = arguments;

            function delayed() {
                if (!execAsap)
                    func.apply(obj, args);
                timeout = null;
            }

            if (timeout)
                clearTimeout(timeout);
            else if (execAsap)
                func.apply(obj, args);

            timeout = setTimeout(delayed, threshold || 100);
        };
    };

    // smartresize 
    jQuery.fn[sr] = function (fn) {
        return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr);
    };

})(jQuery, 'smartresize');
/**
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var CURRENT_URL = window.location.href.split('#')[0].split('?')[0],
    $BODY = $('body'),
    $MENU_TOGGLE = $('#menu_toggle'),
    $SIDEBAR_MENU = $('#sidebar-menu'),
    $SIDEBAR_FOOTER = $('.sidebar-footer'),
    $LEFT_COL = $('.left_col'),
    $RIGHT_COL = $('.right_col'),
    $NAV_MENU = $('.nav_menu'),
    $FOOTER = $('footer');

var CURRENT_URL = window.location.href;

// Sidebar
function init_sidebar() {
// TODO: This is some kind of easy fix, maybe we can improve this
    var setContentHeight = function () {
        var bodyHeight = $BODY.outerHeight(),
            footerHeight = $BODY.hasClass('footer_fixed') ? -10 : $FOOTER.height(),
            leftColHeight = $LEFT_COL.eq(1).height() + $SIDEBAR_FOOTER.height(),
            contentHeight = bodyHeight < leftColHeight ? leftColHeight : bodyHeight;
        contentHeight -= $NAV_MENU.height() + footerHeight;
        contentHeight = $(window).height() - $FOOTER.outerHeight() - $NAV_MENU.height();
        $RIGHT_COL.css('height', contentHeight);
        $(".left_menu").css("height", $(window).height() - $(".left_middle").height());
        var srollHeight = sessionStorage.getItem("srollHeight");
        console.log("获取sessionstorege中的高度: " + srollHeight);
        if (srollHeight) {
            $("#sidebar-menu").scrollTop(srollHeight);
        }
    };

    $SIDEBAR_MENU.find('a').on('click', function (ev) {
        var srollHeight = $("#sidebar-menu").scrollTop();
        sessionStorage.setItem("srollHeight", srollHeight);
        var $li = $(this).parent();
        if ($li.is('.active')) {
            $li.removeClass('active active-sm');
            $('ul:first', $li).slideUp(function () {
                //setContentHeight();
            });
        } else {
            if (!$li.parent().is('.child_menu')) {
                $SIDEBAR_MENU.find('li').removeClass('active active-sm');
                $SIDEBAR_MENU.find('li ul').slideUp();
            } else {
                if ($BODY.is(".nav-sm")) {
                    $SIDEBAR_MENU.find("li").removeClass("active active-sm");
                    $SIDEBAR_MENU.find("li ul").slideUp();
                }
            }

            $li.addClass('active');
            $('ul:first', $li).slideDown(function () {
                //setContentHeight();
            });
            //处理列表的样式
            $SIDEBAR_MENU.find("span").each(function(index){
               $(this).removeClass("fa-chevron-down").addClass("fa-chevron-right");
            });
            $li.find("span").addClass("fa-chevron-down").removeClass("fa-chevron-right")








        }
    });

    // check active menu
    //$SIDEBAR_MENU.find('a[href="' + CURRENT_URL + '"]').parent('li').addClass('current-page');
    initIcon();
    $SIDEBAR_MENU.find('a').filter(function () {
        console.log("this href: "+ this.href);
        return this.href == CURRENT_URL;
    }).parent('li').addClass('current-page').parents('ul').slideDown(function () {
        setContentHeight();
    }).parent().addClass('active');


    setContentHeight();
    // fixed sidebar
    if ($.fn.mCustomScrollbar) {
        $('.menu_fixed').mCustomScrollbar({
            autoHideScrollbar: true,
            theme: 'minimal',
            mouseWheel: {preventDefault: true}
        });
    }
};

function  initIcon(){
    var $PARENT = $SIDEBAR_MENU.find('a').filter(function () {
        return this.href == CURRENT_URL;
    }).parent('li').parent().prev();
    $PARENT.find("span").removeClass("fa-chevron-right").addClass("fa-chevron-down");
}



$(document).ready(function () {
    init_sidebar();
});
	

