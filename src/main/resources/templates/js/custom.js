(function ($) {
 "use strict";
$(document).ready(function(){
	  
    /*
    更多精品模板请访问：http://www.bootstrapmb.com
		Jquery Mobile Menu
		============================*/
		$('#main-menu').meanmenu({
			meanScreenWidth: "767",
			meanMenuContainer: '.mobile-nav-menu',
		});		
		
		/*
		Date Picker
		============================*/	
		$( "#arrival-date" ).datepicker();
		$("#arrival-date").datepicker("option", "showAnim", 'slideDown');
		$( "#outgoing-date" ).datepicker();
		$("#outgoing-date").datepicker("option", "showAnim", 'slideDown');			
		/*
		Room Gallery Crousel
		============================*/ 	
		  $(".all-room-gallary-inner").owlCarousel({
			autoplay: true, 
			pagination:false,
			nav:false, 
			dots:true, 
			items :4,
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				768:{
					items:3
				},				
				1000:{
					items:4
				}
			}			
		  }); 		
		/*
		Testimonial v1 Crousel
		============================*/ 	
		  $(".testimonial-v1-all").owlCarousel({
			autoplay: true, 
			pagination:false,
			nav:false, 
			dots:true, 
			margin:30,
			items :2,
			navText:["<i class='fa fa-angle-left'></i>","<i class='fa fa-angle-right'></i>"],
			responsive:{
				0:{
					items:1
				},
				600:{
					items:1
				},
				768:{
					items:2
				},				
				992:{
					items:2
				}
			}
		  }); 			
	
		/*
		Slider Crousel
		============================*/ 
		$(".all-slide").owlCarousel({
            items: 1,
            nav: false,
            dots: true,
            autoplay: true,			
            loop: true,
            navText: ["<i class='fa fa-angle-left'></i>", "<i class='fa fa-angle-right'></i>"],
            mouseDrag: false,
            touchDrag: false,
        });
        
        $(".all-slide").on("translate.owl.carousel", function(){	
            $(".slider-sub-title").removeClass("animated fadeInUp").css("opacity", "0");
            $(".slider-text h1").removeClass("animated fadeInUp").css("opacity", "0");
            $(".slider-text p").removeClass("animated fadeInDown").css("opacity", "0");
            $(".slider-text ul").removeClass("animated fadeInDown").css("opacity", "0");
        });
        
        $(".all-slide").on("translated.owl.carousel", function(){
            $(".slider-sub-title").addClass("animated fadeInUp").css("opacity", "1");
            $(".slider-text h1").addClass("animated fadeInUp").css("opacity", "1");
            $(".slider-text p").addClass("animated fadeInDown").css("opacity", "1");
            $(".slider-text ul").addClass("animated fadeInDown").css("opacity", "1");
        });	
		
		  
		/*
		scrollUp
		============================*/	
		$.scrollUp({
			scrollText: '<i class="fa fa-long-arrow-up"></i>',
			easingType: 'linear',
			scrollSpeed: 900,
			animation: 'fade'
		});	
		/*
		Counter Js
		============================*/ 
        $('.counter').counterUp({
            delay: 10,
            time: 1000			
        });
		
		/*
		Stikey Js
		============================*/ 
   		(function () {
			var nav = $('.hd-sec');
			var scrolled = false;
			$(window).scroll(function () {
				if (120 < $(window).scrollTop() && !scrolled) {
					nav.addClass('sticky_menu animated fadeInDown').animate({ 'margin-top': '0px' });
					scrolled = true;
				}
				if (120 > $(window).scrollTop() && scrolled) {
					nav.removeClass('sticky_menu animated fadeInDown').css('margin-top', '0px');
					scrolled = false;
				}
			});
		}()); 
/*
		Google Map
		============================*/
    // When the window has finished loading create our google map below
   		if ($("#gmap").length)
   		{
   		    google.maps.event.addDomListener(window, 'load', init);
   		}
            
        
            function init() {
                // Basic options for a simple Google Map
                // For more options see: https://developers.google.com/maps/documentation/javascript/reference#MapOptions
                var mapOptions = {
                    // How zoomed in you want the map to start at (always required)
                    zoom: 11,

                    // The latitude and longitude to center the map (always required)
                    center: new google.maps.LatLng(55.378052, -3.435973), // New York

                    // How you would like to style the map. 
                    // This is where you would paste any style found on Snazzy Maps.
                    styles: [{"featureType":"administrative.country","elementType":"geometry","stylers":[{"visibility":"simplified"},{"hue":"#ff0000"}]}]
                };

                // Get the HTML DOM element that will contain your map 
                // We are using a div with id="map" seen below in the <body>
                var mapElement = document.getElementById('gmap');

                // Create the Google Map using our element and options defined above
                var map = new google.maps.Map(mapElement, mapOptions);

                // Let's also add a marker while we're at it
                var marker = new google.maps.Marker({
                    position: new google.maps.LatLng(55.378052, -3.435973),
                    map: map,
                    title: 'UK!'
                });
            }				
		/*
		Magnific Popup
		============================*/ 		
        $('.gallery-photo').magnificPopup({
            type: 'image',
            gallery: {
              enabled: true
            },
        });	 

		/*
		Project Gallery Js
		============================*/	
		jQuery('.gallery-container').imagesLoaded( function() {			
			$(".gallery-container").isotope({
			itemSelector: '.filtr-item',
			layoutMode: 'fitRows',
			});
			$("ul.simplefilter li").on("click",function(){
			$("ul.simplefilter li").removeClass("active");
			$(this).addClass("active");
			 
			var selector = $(this).attr('data-filter');
			$(".gallery-container").isotope({
			filter: selector,
			animationOptions: {
			duration: 750,
			easing: 'linear',
			queue: false,
			}
			});
			return false;
			});
		});	
		


		/*
		Preeloader
		============================*/
		$(window).on("load", function() {
			$('#preloader').fadeOut();
			$('#preloader-status').delay(200).fadeOut('slow');
			$('body').delay(200).css({'overflow-x':'hidden'});
		});

		
		/*
		Countdown Menu
		============================*/
		const countdown = new Date("May 7, 2021");
		function getRemainingTime(endtime) {
		  const milliseconds = Date.parse(endtime) - Date.parse(new Date());
		  const seconds = Math.floor( (milliseconds/1000) % 60 );
		  const minutes = Math.floor( (milliseconds/1000/60) % 60 );
		  const hours = Math.floor( (milliseconds/(1000*60*60)) % 24 );
		  const days = Math.floor( milliseconds/(1000*60*60*24) );
		  return {
		    'total': milliseconds,
		    'seconds': seconds,
		    'minutes': minutes,
		    'hours': hours,
		    'days': days,
		  };
		}
		  
		function initClock(id, endtime) {
		  const counter = document.getElementById(id);
		  const daysItem = counter.querySelector('.days');
		  const hoursItem = counter.querySelector('.hours');
		  const minutesItem = counter.querySelector('.minutes');
		  const secondsItem = counter.querySelector('.seconds');

		  function updateClock() {
		    const time = getRemainingTime(endtime);
		    daysItem.innerHTML = time.days;
		    hoursItem.innerHTML = ('0' + time.hours).slice(-2);
		    minutesItem.innerHTML = ('0' + time.minutes).slice(-2);
		    secondsItem.innerHTML = ('0' + time.seconds).slice(-2);

		    if (time.total <= 0) {
		      clearInterval(timeinterval);
		    }
		  }
		  updateClock();
		  const timeinterval = setInterval(updateClock, 1000);
		}
		if ($("#js-countdown").length)
		{
		    initClock('js-countdown', countdown);
		}
		

		
		
	});	
})(jQuery);

