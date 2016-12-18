/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function search() {
    let username = $("#username-searchkey").val();
    let requestUri = '/posts?username=' + username;
    $.getJSON(requestUri, function (posts) {
        $("#dveedlist").empty();

        posts.forEach(function (post) {
            
            let date = new Date(post.date);
            let tags = '';
            post.tags.forEach(function(tag) {
                tags += tag.name + ' ';
            });
            
            let child = '<li>' + post.content + '<ul>' +
                        '<li>user: ' + username + '</li>' +
                        '<li>tags: tags not supported yet</li>' +
                        '<li>' + date + '</li>' +
                        '<li>tags: ' + tags + '</li>' +
                        '</ul></li>';
            $("#dveedlist").append(child);
        });
    });
}