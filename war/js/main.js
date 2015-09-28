jQuery(function($) {

	// #main-slider
	$(function() {
		$('#main-slider.carousel').carousel({
			interval : 8000
		});
	});

	$('.centered').each(
			function(e) {
				$(this).css('margin-top',
						($('#main-slider').height() - $(this).height()) / 2);
			});

	$(window).resize(
			function() {
				$('.centered').each(
						function(e) {
							$(this).css(
									'margin-top',
									($('#main-slider').height() - $(this)
											.height()) / 2);
						});
			});

	// portfolio
	$(window).load(function() {
		$portfolio_selectors = $('.portfolio-filter >li>a');
		if ($portfolio_selectors != 'undefined') {
			$portfolio = $('.portfolio-items');
			$portfolio.isotope({
				itemSelector : 'li',
				layoutMode : 'fitRows'
			});
			$portfolio_selectors.on('click', function() {
				$portfolio_selectors.removeClass('active');
				$(this).addClass('active');
				var selector = $(this).attr('data-filter');
				$portfolio.isotope({
					filter : selector
				});
				return false;
			});
		}
	});

	// contact form
	var form = $('.contact-form');
	form.submit(function() {
		$this = $(this);
		$.post($(this).attr('action'), function(data) {
			$this.prev().text(data.message).fadeIn().delay(3000).fadeOut();
		}, 'json');
		return false;
	});

	// goto top
	$('.gototop').click(function(event) {
		event.preventDefault();
		$('html, body').animate({
			scrollTop : $("body").offset().top
		}, 500);
	});

	// Pretty Photo
	$("a[rel^='prettyPhoto']").prettyPhoto({
		social_tools : false
	});
});

//Google Analytics
(function(i, s, o, g, r, a, m) {
	i['GoogleAnalyticsObject'] = r;
	i[r] = i[r] || function() {
		(i[r].q = i[r].q || []).push(arguments)
	}, i[r].l = 1 * new Date();
	a = s.createElement(o), m = s.getElementsByTagName(o)[0];
	a.async = 1;
	a.src = g;
	m.parentNode.insertBefore(a, m)
})(window, document, 'script', '//www.google-analytics.com/analytics.js', 'ga');

ga('create', 'UA-68146338-1', 'auto');
ga('send', 'pageview');
