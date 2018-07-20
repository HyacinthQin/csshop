$(function(){
	var shopId=getQueryString("shopId");
	var shopInfoUrl='/csshop/shop/getshopmanagementinfo?shopId='+shopId;
	$.getJSON(shopInfoUrl,function(data){
		if(data.redirect){
			window.location.href=data.url;
		}else{
			if(data.shopId!=undefined && data.shopId!=null){
				shopId=data.shopId;
			}
			$('#shopInfo').attr('href','/csshop/shop/shopoperation?shopId='+shopId);
		}
	})
})