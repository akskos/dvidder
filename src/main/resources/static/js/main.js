/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var account = "";

$(document).ready(function() {
   
   console.log(getCSRFToken());
   
    // Find username
    $.ajax({
        url: '/account',
        type: 'GET',
        success: function(result) {
            account = result;
            console.log('my username is ' + result.username);
        }
    })
});

function constructPostHTML(post) {
    let date = new Date(post.date);
    let tags = '';
    post.tags.forEach(function(tag) {
        tags += tag.name + ' ';
    });

    let child = '<li>' + post.content + '<ul>' +
                '<li>user: ' + post.sender + '</li>' +
                '<li>' + date + '</li>' +
                '<li>tags: ' + tags + '</li>';

    if (account.username === post.sender) {
        child += '<li><a href="#" onclick="deletePost(' + post.postId + ')">Delete</a></li>';
    }
    
    if (account.admin) {
        child += '<li><a href="#" onclick="deleteAccount(\'' + post.sender + '\')">Delete account</a></li>';
    }
    
    child += '<li>Likes: ' + post.likers.length + '</li>'
    child += '<li><a href="#" onclick="likePost(' + post.postId + ')">Like</a></li>'

    child += '</ul></li>';
    
    return child;
}

function searchByUser() {
    let username = $("#username-searchkey").val();
    let requestUri = '/posts?username=' + username;
    $.getJSON(requestUri, function (posts) {
        $("#dveedlist").empty();

        posts.forEach(function (post) {
            
            var postHTML = constructPostHTML(post);
            $("#dveedlist").append(postHTML);
        });
    });
}

function searchByTag() {
    let tag = $("#tag-searchkey").val();
    let requestUri = '/posts?tag=' + tag;
    $.getJSON(requestUri, function (posts) {
        $("#dveedlist").empty();

        posts.forEach(function (post) {
            
            var postHTML = constructPostHTML(post);
            $("#dveedlist").append(postHTML);
        });
    });
}

function deletePost(id) {
    console.log('deleting post ' + id);
    $.ajax({
        url: '/post/' + id,
        type: 'delete',
        headers: {
            'X-CSRF-TOKEN': getCSRFToken()
        },
        success: function(result) {
            
        }
    });
}

function deleteAccount(username) {
    console.log('deleting account ' + username);
    $.ajax({
       url: '/account/' + username,
       type: 'delete',
       headers: {
           'X-CSRF-TOKEN': getCSRFToken()
       },
       success: function(result) {
           
       }
    });
}

function likePost(id) {
    console.log('liking post ' + id);
    $.ajax({
        url: '/posts/' + id + '/like',
        type: 'post',
        headers: {
            'X-CSRF-TOKEN': getCSRFToken()
        },
        success: function(result) {
            
        }
    })
}

function getCSRFToken() {
    return $('meta[name="_csrf"]').attr('content');
}