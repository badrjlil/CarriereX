jQuery(function($) {
	'use strict'; jQuery('.mean-menu').meanmenu({ meanScreenWidth: "991" }); $(window).on('scroll', function() {
		/* if ($(this).scrollTop() > 0) { $('.navbar-area').addClass("is-sticky"); }
		 else { $('.navbar-area').removeClass("is-sticky"); }*/
	}); $('.condidate-slider').owlCarousel({ loop: true, margin: 30, nav: false, smartSpeed: 1500, dots: true, responsive: { 0: { items: 1 }, 600: { items: 2 }, 992: { items: 3 }, 1200: { items: 4 } } })
	$('.testimonial-slider').owlCarousel({ loop: true, margin: 30, nav: true, dots: false, items: 1, smartSpeed: 2500, autoplay: false, autoplayTimeout: 4000, navText: ["<i class='bx bx-chevrons-left'></i>", "<i class='bx bx-chevrons-right bx-tada'></i>"] })
	$('select').niceSelect(); $('.testimonial-slider-two').owlCarousel({ loop: true, margin: 30, nav: true, dots: false, smartSpeed: 2500, autoplay: false, autoplayTimeout: 4000, navText: ["<i class='bx bx-chevrons-left bx-tada'></i>", "<i class='bx bx-chevrons-right bx-tada'></i>"], responsive: { 0: { items: 1 }, 768: { items: 2 } } })
	$('.popup-youtube').magnificPopup({ disableOn: 320, type: 'iframe', mainClass: 'mfp-fade', removalDelay: 160, preloader: false, fixedContentPos: false }); $(".newsletter-form").validator().on("submit", function(event) { if (event.isDefaultPrevented()) { formErrorSub(); submitMSGSub(false, "Please enter your email correctly."); } else { event.preventDefault(); } }); function callbackFunction(resp) {
		if (resp.result === "success") { formSuccessSub(); }
		else { formErrorSub(); }
	}
	function formSuccessSub() { $(".newsletter-form")[0].reset(); submitMSGSub(true, "Thank you for subscribing!"); setTimeout(function() { $("#validator-newsletter").addClass('hide'); }, 4000) }
	function formErrorSub() { $(".newsletter-form").addClass("animated shake"); setTimeout(function() { $(".newsletter-form").removeClass("animated shake"); }, 1000) }
	function submitMSGSub(valid, msg) {
		if (valid) { var msgClasses = "validation-success"; } else { var msgClasses = "validation-danger"; }
		$("#validator-newsletter").removeClass().addClass(msgClasses).text(msg);
	}
	$(".newsletter-form").ajaxChimp({ url: "https://envytheme.us20.list-manage.com/subscribe/post?u=60e1ffe2e8a68ce1204cd39a5&amp;id=42d6d188d9", callback: callbackFunction }); $(".accordion-title").click(function(e) { var accordionitem = $(this).attr("data-tab"); $("#" + accordionitem).slideToggle().parent().siblings().find(".accordion-content").slideUp(); $(this).toggleClass("active-title"); $("#" + accordionitem).parent().siblings().find(".accordion-title").removeClass("active-title"); }); $(window).scroll(function() {
		if ($(this).scrollTop() != 0) { $('.top-btn').addClass('active'); }
		else { $('.top-btn').removeClass('active'); }
	}); $('.top-btn').on('click', function() { $("html, body").animate({ scrollTop: 0 }, 2500); return false; }); $(window).on('load', function() { $(".loader-content").fadeOut(200); })
	$('body').append("");
}(jQuery)); function setTheme(themeName) { localStorage.setItem('jovie_theme', themeName); document.documentElement.className = themeName; }

document.addEventListener('DOMContentLoaded', function() {
	// Get the current path
	const path = window.location.pathname;

	// Check the path and set the active class accordingly
		if (path.includes('find-job')) {
		document.getElementById('find-job-nav-item').classList.add('active');
	} else if (path.includes('catagories')) {
		document.getElementById('categories-nav-item').classList.add('active');
	} else if (path.includes('about')) {
		document.getElementById('about-nav-item').classList.add('active');
	} else if (path.includes('contact')) {
		document.getElementById('contact-nav-item').classList.add('active');
	} else if (path.includes('testimonial')) {
		document.getElementById('plus-nav-item').classList.add('active');
	} else if (path.includes('faq')) {
		document.getElementById('plus-nav-item').classList.add('active');
	}else if (path.includes('account')) {
		document.getElementById('profil-nav-item').classList.add('active');
	} else if (path.includes('emploiApplique')) {
		document.getElementById('emploi-nav-item').classList.add('active');
	} else if (path.includes('changePassword')) {
		document.getElementById('password-nav-item').classList.add('active');
	}
	
});