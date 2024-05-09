/*---------------------------------------------
Template name:  Zobstar - Job Board HTML Template
Version:        1.0
Author:         TechyDevs
Author Email:   contact@tecydevs.com

[Table of Content]

01: Preloader
02: side-widget-menu
02: Mobile Menu Open Control
03: Mobile Menu Close Control
04: Side user panel menu Open Control
05: Back to Top Button and Navbar Scrolling Effects
06: back to top button click control
07: most-visited-wrap
08: Client logo slider
09: client-testimonial
10: gallery-carousel
11: magnificpopup
12: Daterangepicker
13: MagnificPopup
14: Quantity number increment control
15: Quantity number decrement control
16: Tooltip
17: Nice Select
18: Counter up
----------------------------------------------*/

(function ($) {
    "use strict";

    var $window = $(window);

    $window.on('load', function () {

        var $document = $(document);
        var $dom = $('html, body');
        var preLoader = $('.loader-container');
        var skillbar = $('.skillbar');
        var headerMenuWrapper = $('.header-menu-wrapper');
        var backToTop = $('#back-to-top');
        var cardCarousel = $('.card-carousel');
        var clientLogo = $('.client-logo');
        var testimonialCarousel = $('.testimonial-carousel');
        var testimonialCarouselTwo = $('.testimonial-carousel-2');
        var galleryCarousel = $('.gallery-carousel');
        var dateRangePicker = $('input[name="daterange"]');
        var userChosenSelect = $('.user-chosen-select');
        var teamCarousel = $('.team-carousel');
        var popularJobCarousel = $('.popular-job-carousel');
        var circleChart = $('.circlechart');
        var userTextEditor = $('.user-text-editor');
        var userTextEditorTwo = $('.user-text-editor-2');
        var userTextEditorThree = $('.user-text-editor-3');
        var userTextEditorFour = $('.user-text-editor-4');
        var emojiPicker = $('.emoji-picker');
        var videoPopupBtn = $('.video-popup-btn');


        /*==== Preloader =====*/
        $(preLoader).delay('500').fadeOut(2000);

        /*====  side-widget-menu  =====*/
        $document.on('click','.side-menu-wrap .side-menu-ul>li>a .btn-toggle', function () {
            $(this).closest('li').siblings().removeClass('active').find('.dropdown-menu-item').slideUp(200);
            $(this).closest('li').toggleClass('active').find('.dropdown-menu-item').slideToggle(200);
            return false;
        });

        /*=========== side-nav-container Menu Open Control ============*/
        $document.on('click','.logo-right-content .side-menu-open', function () {
            $('.side-nav-container').toggleClass('active');
        });

        /*=========== dashboard-nav-container Menu Open Control ============*/
        $document.on('click','.dashboard-nav-trigger-btn', function () {
            $('.dashboard-nav-container').addClass('active');
        });

        /*=========== user-nav-container Menu Open Control ============*/
        $document.on('click','.user-menu-open', function () {
            $('.user-nav-container').addClass('active');
        });

        /*=========== Mobile Menu Close Control ============*/
        $document.on('click','.humburger-menu .side-menu-close', function () {
            $(".side-nav-container, .dashboard-nav-container, .user-nav-container").removeClass('active');
        });

        /*=========== Skillbar ============*/
        $(skillbar).each(function() {
            $(this).find('.skill-item').animate({ width: $(this).attr('data-percent') }, 3000);
        });

        /*===== Back to Top Button and Navbar Fixed ======*/
        $window.on('scroll', function() {
            //header fixed animation and control
            if($window.scrollTop() > 0) {
                $(headerMenuWrapper).addClass('header-fixed');
            }else{
                $(headerMenuWrapper).removeClass('header-fixed');
            }

            //Show back to top
            if ($window.scrollTop() > 500) {
                $(backToTop).addClass('btn-active');
            } else {
                $(backToTop).removeClass('btn-active');
            }

        });

        /*===== back to top ======*/
        $document.on('click', '#back-to-top', function() {
            $($dom).animate({
                scrollTop: 0
            }, 800);
            return false;
        });

        /*==== card-carousel =====*/
        if ($(cardCarousel).length) {
            $(cardCarousel).owlCarousel({
                loop: true,
                items: 2,
                nav: false,
                dots: false,
                autoplay: true,
                smartSpeed: 500,
                margin: 30,
                responsive : {
                    // breakpoint from 0 up
                    0 : {
                        items: 1
                    },
                    // breakpoint from 768 up
                    768 : {
                        items: 2
                    }
                }
            });
        }

        /*==== Client logo =====*/
        if ($(clientLogo).length) {
            $(clientLogo).owlCarousel({
                loop: true,
                items: 6,
                nav: false,
                dots: false,
                smartSpeed: 700,
                autoplay: true,
                responsive : {
                    // breakpoint from 0 up
                    0 : {
                        items: 1
                    },
                    // breakpoint from 425 up
                    425 : {
                        items: 2
                    },
                    // breakpoint from 480 up
                    480 : {
                        items: 2
                    },
                    // breakpoint from 767 up
                    767 : {
                        items: 4
                    },
                    // breakpoint from 992 up
                    992 : {
                        items: 6
                    }
                }
            });
        }

        /*==== testimonial-carousel =====*/
        if ($(testimonialCarousel).length) {
            $(testimonialCarousel).owlCarousel({
                loop: true,
                items: 3,
                nav: false,
                dots: true,
                smartSpeed: 700,
                autoplay: false,
                margin: 30,
                responsive : {
                    // breakpoint from 0 up
                    0 : {
                        items: 1
                    },
                    // breakpoint from 768 up
                    768 : {
                        items: 2
                    },
                    // breakpoint from 992 up
                    992 : {
                        items: 3
                    }
                }
            });
        }

        /*==== testimonial-carousel-2 =====*/
        if ($(testimonialCarouselTwo).length) {
            $(testimonialCarouselTwo).owlCarousel({
                loop: true,
                items: 5,
                nav: false,
                dots: true,
                smartSpeed: 700,
                autoplay: false,
                margin: 30,
                responsive : {
                    // breakpoint from 0 up
                    0 : {
                        items: 1
                    },
                    // breakpoint from 480 up
                    576 : {
                        items: 2
                    },
                    // breakpoint from 991 up
                    991 : {
                        items: 3
                    },
                    // breakpoint from 992 up
                    992 : {
                        items: 4
                    },
                    // breakpoint from 1200 up
                    1200 : {
                        items: 5
                    }
                }
            });
        }

        /*==== gallery-carousel =====*/
        if ($(galleryCarousel).length) {
            $(galleryCarousel).owlCarousel({
                loop: true,
                items: 1,
                nav: true,
                dots: true,
                smartSpeed: 700,
                autoplay: false,
                dotsData: true,
                navText: ["<i class=\"la la-chevron-left\"></i>", "<i class=\"la la-chevron-right\"></i>"]
            });
        }

        /*==== fancybox =====*/
        if(videoPopupBtn.length){
            Fancybox.bind('[data-fancybox]', {
                // Your custom options
            });
        }
        

        /*==== Daterangepicker =====*/
        if ($(dateRangePicker).length) {
            $(dateRangePicker).daterangepicker({
                opens: 'right',
                singleDatePicker: true
            });
        }

        /*==== Quantity number increment control =====*/
        $document.on('click', '.input-number-increment', function() {
            var $input = $(this).parents('.input-number-group').find('.input-number');
            var val = parseInt($input.val(), 10);
            $input.val(val + 1);

        });

        /*==== Quantity number decrement control =====*/
        $document.on('click', '.input-number-decrement', function() {
            var $input = $(this).parents('.input-number-group').find('.input-number');
            var val = parseInt($input.val(), 10);
            $input.val(val - 1);
        });

        /*======= ui range slider ========*/
        if ($("#slider-range").length) {
            $( "#slider-range" ).slider({
                range: true,
                min: 0,
                max: 500,
                values: [ 50, 290 ],
                slide: function( event, ui ) {
                    $( "#amount" ).val( "$" + ui.values[ 0 ] + " - $" + ui.values[ 1 ] );
                }
            });
        }

        $( "#amount" ).val( "$" + $( "#slider-range" ).slider( "values", 0 ) +
            " - $" + $( "#slider-range" ).slider( "values", 1 ) );


        /*==== Chosen select =====*/
        if ($(userChosenSelect).length) {
            $(userChosenSelect).chosen({
                no_results_text: "Oops, nothing found!",
                allow_single_deselect: true
            });
        }

        $(userChosenSelect).on('chosen:showing_dropdown', function(event, params) {
            var chosen_container = $( event.target ).next( '.chosen-container' );
            var dropdown = chosen_container.find( '.chosen-drop' );
            var dropdown_top = dropdown.offset().top - $window.scrollTop();
            var dropdown_height = dropdown.height();
            var viewport_height = $window.height();

            if ( dropdown_top + dropdown_height > viewport_height ) {
                chosen_container.addClass( 'chosen-drop-up' );
            }
        });

        $(userChosenSelect).on('chosen:hiding_dropdown', function(event, params) {
            $( event.target ).next( '.chosen-container' ).removeClass( 'chosen-drop-up' );
        });

        /*==== team-carousel =====*/
        if ($(teamCarousel).length) {
            $(teamCarousel).owlCarousel({
                loop: true,
                items: 3,
                nav: true,
                dots: true,
                smartSpeed: 500,
                autoplay: false,
                margin: 30,
                navText: ["<i class='la la-angle-left'></i>", "<i class='la la-angle-right'></i>"],
                responsive : {
                    // breakpoint from 0 up
                    0 : {
                        items: 1
                    },
                    // breakpoint from 768 up
                    768 : {
                        items: 2
                    },
                    // breakpoint from 991 up
                    991: {
                        items: 2
                    },
                    // breakpoint from 992 up
                    992: {
                        items: 3
                    }
                }
            });
        }

        /*==== popular-job-carousel =====*/
        if ($(popularJobCarousel).length) {
            $(popularJobCarousel).owlCarousel({
                loop: true,
                items: 1,
                nav: false,
                dots: false,
                autoplay: true
            });
        }

        /*=========== circlechart ============*/
        if ($(circleChart).length) {
            $(circleChart).circlechart();
        }

        /*====  Tooltip =====*/
        $('[data-bs-toggle="tooltip"]').tooltip();

        /*=========== Google map ============*/
        if($("#map").length) {
            initMap('map', 40.717499, -74.044113, 'images/map-marker.png');
        }

        /*==== jqte text editor =====*/
        if ($(userTextEditor).length) {
            $(userTextEditor).jqte({
                placeholder: "Write your job description",
                formats: [
                    ["p","Paragraph"],
                    ["h1","Heading 1"],
                    ["h2","Heading 2"],
                    ["h3","Heading 3"],
                    ["h4","Heading 4"],
                    ["h5","Heading 5"],
                    ["h6","Heading 6"],
                    ["pre","Preformatted"]
                ]
            });
        }

        if ($(userTextEditorTwo).length) {
            $(userTextEditorTwo).jqte({
                placeholder: "Briefly describe about your company",
                formats: [
                    ["p","Paragraph"],
                    ["h1","Heading 1"],
                    ["h2","Heading 2"],
                    ["h3","Heading 3"],
                    ["h4","Heading 4"],
                    ["h5","Heading 5"],
                    ["h6","Heading 6"],
                    ["pre","Preformatted"]
                ]
            });
        }

        if ($(userTextEditorThree).length) {
            $(userTextEditorThree).jqte({
                placeholder: "Enter long description for your online resume",
                formats: [
                    ["p","Paragraph"],
                    ["h1","Heading 1"],
                    ["h2","Heading 2"],
                    ["h3","Heading 3"],
                    ["h4","Heading 4"],
                    ["h5","Heading 5"],
                    ["h6","Heading 6"],
                    ["pre","Preformatted"]
                ]
            });
        }

        if ($(userTextEditorFour).length) {
            $(userTextEditorFour).jqte({
                placeholder: "Briefly describe about your company where you worked before",
                formats: [
                    ["p","Paragraph"],
                    ["h1","Heading 1"],
                    ["h2","Heading 2"],
                    ["h3","Heading 3"],
                    ["h4","Heading 4"],
                    ["h5","Heading 5"],
                    ["h6","Heading 6"],
                    ["pre","Preformatted"]
                ]
            });
        }

        /*==== emoji-picker =====*/
        if ($(emojiPicker).length) {
            $(emojiPicker).emojioneArea({
                pickerPosition: "top"
            });
        }

        /*========= Ajax contact form ========*/
        var submitBtn = document.querySelector('#send-message-btn');
        var form = $('.contact-form'),
            message = $('.alert-message'),
            formData;

        // Success function
        function doneFunction(response) {
            submitBtn.innerHTML = 'Send Message';
            message.fadeIn().removeClass('alert-danger').addClass('alert-success');
            message.text(response);
            setTimeout(function () {
                message.fadeOut();
            }, 3000);
            form.find('input:not([type="submit"]), textarea').val('');
        }

        // fail function
        function failFunction(data) {
            submitBtn.innerHTML = 'Send Message';
            message.fadeIn().removeClass('alert-success').addClass('alert-danger');
            message.text(data.responseText);
            setTimeout(function () {
                message.fadeOut();
            }, 3000);
        }

        // form submit
        form.submit(function (e) {
            e.preventDefault();
            formData = $(this).serialize();
            submitBtn.innerHTML = 'Sending...';
            setTimeout(function () {
                $.ajax({
                    type: 'POST',
                    url: form.attr('action'),
                    data: formData
                })
                    .done(doneFunction)
                    .fail(failFunction);
            }, 2000)
        });

        /*==== Show/Hide password of input field =====*/
        var togglePassword = $('.toggle-password');
        togglePassword.on('click', function () {
            $(this).toggleClass('active');
            var passInput = $('.password-field');
            if (passInput.attr('type') === 'password') {
                passInput.attr('type', 'text');
            } else {
                passInput.attr('type', 'password');
            }
        });
        
        /*==== copyright date =====*/
        if($("#copyright-date")){
            var currentYear = new Date().getFullYear();
            $("#copyright-date").text(currentYear);
        }

    });

})(jQuery);

