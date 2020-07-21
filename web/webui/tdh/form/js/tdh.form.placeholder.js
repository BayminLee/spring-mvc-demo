/**
 * tdh.form.js 扩展
 * 支持IE9以下的 placeholder 属性 
 * 支持IE浏览器，其他浏览器未做支持
 * 目前只支持.inputText元素(text,textarea)  
 */
jQuery(function(){
	placeholderInit(".inputText[placeholder]");
})

function placeholderInit(obj){
	if(!obj.length || 'placeholder' in document.createElement('input')){	//浏览器支持placeholder属性 不做处理
		return;
	}
	var radioBoxs=ifdom(obj);
	radioBoxs.each(function(){
		var othor = this;
		var $this = jQuery(this);
		if($this.parent().find("span.placeholder").length){
			$this.parent().find("span.placeholder").text($this.attr("placeholder"));
			return true;
		}
        var left = "10px";	//$this.css("padding-left");
        var top =  $this.css("padding-top");
        var $pdiv = jQuery("<div style='position:relative;' class='placeholder-box'></div>");
        $this.parent().append($pdiv);
        $this.appendTo($pdiv);
        $this.parent().append('<span class="placeholder" style="position:absolute;display: inline-block;width:auto;height:24px;line-height:24px; z-index: 10;color: #C7C7CD;left: ' + left + ';top:'+top+'">' + $this.attr("placeholder") + '</span>');
        if($this.val() != ""){
            $this.parent().find("span.placeholder").hide();
        } else{
            $this.parent().find("span.placeholder").show();
        }
        
        //$this.addEventListener ('DOMAttrModified', OnAttrModified, false);   //TODO 兼容其他非ie浏览器可在此修改
        othor.attachEvent ('onpropertychange', function(event){  	// ie
        	if(event.propertyName === "value"){
        		if(!event.srcElement.attributes[event.propertyName].value){
        			jQuery(event.srcElement).parent().find("span.placeholder").show();
        		}else{
        			jQuery(event.srcElement).parent().find("span.placeholder").hide();
        		}
        	}else if(event.propertyName === "placeholder"){
        		jQuery(event.srcElement).parent().find("span.placeholder").text(event.srcElement.attributes[event.propertyName].value);
        	}
        });
	}).on("focus", function(){
		jQuery(this).parent().find("span.placeholder").hide();
    }).on("blur", function(){
        var $this = jQuery(this);
        if($this.val() != ""){
            $this.parent().find("span.placeholder").hide();
        }else{
            $this.parent().find("span.placeholder").show();
        }
    });
	
	// 点击表示placeholder的标签相当于触发input
    $("span.placeholder").on("click", function(){
    	jQuery(this).hide();
    	jQuery(this).siblings("[placeholder]").trigger("click");
    	jQuery(this).siblings("[placeholder]").trigger("focus");
    });
}