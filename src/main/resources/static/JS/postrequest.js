$( document ).ready(function() {
  
  // SUBMIT FORM
   
    
    
    function ajaxPost(urlF,dateF,priceF){
      
      // PREPARE FORM DATA
      var formData = {
        url : urlF,
        date :  dateF,
        price :  priceF
      }
      //console.log(formData);
      console.log(JSON.stringify(formData),"hah");
      // DO POST
      $.ajax({
      type : "POST",
      contentType : "application/json; charset=utf-8",
      url : window.location + "welcome/update/save",
      data : JSON.stringify(formData),
      dataType : 'json',
      success : function(result) {
        if(result.status == "Done"){
          $("#successValue").html(
            "<input class='form-control form-control-lg' type='text' placeholder='.form-control-lg' value='"+result.data.url+"''>"+
"<input class='form-control' type='text' placeholder='Default input' value='"+result.data.date+"'>"+
"<input class='form-control form-control-sm' type='text' placeholder='.form-control-sm' value='"+result.data.price+"'>"
        
                        
                        
                        );
        }else{
          $("#postResultDiv").html("<strong>Error</strong>");
        }
        //console.log(result);
      },
      error : function(e) {
        //alert("Error!")
        console.log("ERROR: ", e);
      }
    });
      
      // Reset FormData after Posting
      resetData();
 
    }
    
    function resetData(){
      $("#url").val("");
      $("#date").val("");
      $("#price").val("");
    }
    
    
    
    
    var url_home = "";

document.getElementById("urlSubmit").addEventListener("click", function(event){
    if(document.getElementById("urlValue").value != null)
    {
        url_home=document.getElementById("urlValue").value;    
        console.log(url_home);
        document.getElementById("urlValue").value="";
        mobileNext(url_home);
        //mobileHome(url_home);
    }
    event.preventDefault();
  });
    

  //$("#customerForm").submit(function(event) {
    // Prevent the form from submitting via the browser.
   // event.preventDefault();
    
  //});

  
  
let url_next = "" ;
var page=0;
var isPaused = false;

function mobileNext(url_next)
{
    if(!isPaused)
    {
        isPaused=true;
        fetch(url_next).then(function (response) {
        return response.text();
    }).then(function (html) {
      
        // Convert the HTML string into a document object
        var parser = new DOMParser();
        var doc = parser.parseFromString(html, 'text/html');
        var filteredData_links = doc.getElementsByClassName("s-main-slot s-result-list s-search-results sg-row")[0].getElementsByClassName("a-link-normal s-no-hover a-text-normal");
        var filteredData_price = doc.getElementsByClassName("s-main-slot s-result-list s-search-results sg-row")[0].getElementsByClassName("a-price-whole");

        nextPageHome = doc.getElementsByClassName("a-selected")[0].nextSibling.nextSibling.getElementsByTagName('a')[0].href;
        nextPageHome =  "https://www.amazon.in"+nextPageHome.slice(nextPageHome.search("\/[\s]"));
    
        url_next = nextPageHome;
        var bar = new Promise((resolve,reject)=>{
          for (var data=0;data<filteredData_links.length;data++) {
             
             // console.log(filteredData_price[data].innerText);
             // console.log("https://www.amazon.in"+filteredData_links[data].getAttribute('href'));
              var finalUrl="https://www.amazon.in"+filteredData_links[data].getAttribute('href');
              finalUrl=finalUrl.split("/ref")[0];
              var finalPrice=filteredData_price[data].innerText;
              let today = new Date().toISOString().slice(0, 10);
              if(!finalUrl.includes("slredirect"))
              {
              ajaxPost(finalUrl,today,finalPrice);
              }
              console.log("--------------------------");

              if(data==filteredData_links.length-1)
              {
                  page++;   
                  console.log(page);
                  resolve();
              }
          }
        });
        bar.then(() => {
            isPaused=false;
            if(page<399)
            {
            setTimeout( function(){
            mobileNext(url_next);}, 1000);  
        }
      });
      }).catch(function (err) {
          console.warn('Something went wrong.', err);
      });
    }
}
  
  
function haha()
{
console.log("adsdfgh");

}  

function mobileHome(url_home)
{
    if(!isPaused)
    {
        isPaused=true;
        fetch(url_home).then(function (response) {
            return response.text();
          }).then(function (html) {
            // Convert the HTML string into a document object
            var parser = new DOMParser();
            var doc = parser.parseFromString(html, 'text/html');
            var nextPageHome = doc.getElementsByClassName("pagnRA")[0].getElementsByTagName('a')[0].href;
            
            nextPageHome = "https://www.amazon.in/s/" + nextPageHome.slice(nextPageHome.search("ref"));
            url_next = nextPageHome;
            var bar = new Promise((resolve,reject)=>{
            var indexx =0
            for(indexx =0;indexx<24;indexx++)
            {
                var fetchId = "result_"+indexx;
                var link = doc.getElementById(fetchId).getElementsByTagName('a');
                var price = doc.getElementById(fetchId).getElementsByTagName("span");
                var hrefs = [];
                var finalPrice;
                var finalUrl;
                for(var i=0; i < link.length; i++){
                if((link[i].href).includes("www.amazon.in"))
                {
                    // console.log(link[i].href);
                    finalUrl=link[i].href;
                    finalUrl=finalUrl.split("/ref")[0];
                    break;
                }
                }
                
                for(var i=0; i < price.length; i++){
                    if(price[i].className == "a-size-base a-color-price s-price a-text-bold")
                    {
                        // console.log(price[i].innerText);
                        finalPrice=price[i].innerText;
                    }
                }
                
         
                let today = new Date().toISOString().slice(0, 10)
              if(!finalUrl.includes("slredirect"))
              {
              ajaxPost(finalUrl,today,finalPrice);
              }
               // ajaxPost(finalUrl,today,finalPrice);
                
                console.log("------------------------------------------------------");
          
                if(indexx==23)
                {
                    page++;
                console.log(page);
                    resolve();
                }
            }
            });
             
            bar.then(() => {
                isPaused=false;
                mobileNext(url_next);
        });
            
        }).catch(function (err) {
            // There was an error
            console.warn('Something went wrong.', err);
        });
        
    }
}
    
    
    
    
    
    
    
})