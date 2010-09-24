// Automatically updates a page by querying the given URL for the given
// amount of time (in milliseconds).  It is assumed that the URL supplied
// will return JSON data.  The JSON data is also assumed to be a hash of
// key=value pairs where each key represents a CSS selector ID and the value
// is the HTML that is to be replaced at the given CSS selector ID.
function autoRefresh(url, time) {
    if (url != undefined && time != undefined) {
        $.getJSON(url, function(data) {
            if (data != undefined) {
                for (x in data)
                { $("#" + x).html(data[x]); }
            }
        });        
        setTimeout("autoRefresh('" + url + "', " + time + ")", time);
    }
}