/**
 * Date format function
 */
/* Demised, and replaced by getFormatStr()
 * Date.prototype.getYYYYMMDD=function(){
    //var d = new Date();
    var date = this.getDate();
    var month = this.getMonth() + 1; 
    var year = this.getFullYear();
    String(month).length < 2 ? (month = "0" + month): month;
    String(date).length < 2 ? (date = "0" + date): date;
    var result = year + "" + month +""+ date;
    return result;
} */ 

Date.prototype.getFormatStr=function(format){
    //var d = new Date();
	let result = null;
	
    let date = this.getDate();
    let month = this.getMonth() + 1; 
    let year = this.getFullYear();
    let hh = this.getHours();
    let mi = this.getMinutes();
    let ss = this.getSeconds();
    String(month).length < 2 ? (month = "0" + month): month;
    String(date).length < 2 ? (date = "0" + date): date;
    
    format = !!format ? format.toUpperCase() : "YYYYMMDD";
    result = 
    	format.replaceAll("YYYY", year)
    		  .replaceAll("MM", month)
    		  .replaceAll("DD", date)
    		  .replaceAll("HH", hh)
    		  .replaceAll("MI", mi)
    		  .replaceAll("SS", ss);
    /*
     * Demised below method
    if(!!format && format.toUpperCase()=="YYYY-MM-DD"){
    	//!!format to check whether has value, otherwise undefined
    	result = year + "-" + month +"-"+ date;
    }else{//default is "YYYYMMDD"
    	result = year + "" + month +""+ date;
    }
    */
    return result;
}

/**
 * JS String no replaceAll() function, so extend by myself;
 */
String.prototype.replaceAll=function(s1, s2){
	return this.replace(new RegExp(s1,"gm"), s2);
}
