/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var username = "";
var admin = false;

$(document).ready(function() {
   
   console.log(getCSRFToken());
   
    // Find username
    $.ajax({
        url: '/account?param=username',
        type: 'GET',
        success: function(result) {
            username = result;
            console.log('my username is ' + result);
        }
    });
    
    // Am I admin?
    $.ajax({
        url: '/account?param=admin',
        type: 'GET',
        success: function(result) {
            admin = (result === 'true');
        }
    });
});

function constructPostHTML(post) {
    let date = new Date(post.date);
    let tags = '';
    post.tags.forEach(function(tag) {
        tags += tag.name + ' ';
    });

    let child = '<li><div id="post-' + post.postId + '" class=\'post\'><img class="profile-pic" src=\'/account/' + post.sender + '/profilepic\'></img><p id="username">' + post.sender +
                '</p><br /><br /><p>' + post.content + '</p> <br />' +
                '<small>' + date + ' </small>';
        
    if (post.tags.length > 0 && post.tags[0].name.length > 0) {
        child += '<small>tags: ' + tags + '</small>';
    }
            
    child += '<a class="like-link" onclick="likePost(' + post.postId + ')">Like (' + numberOfLikes(post) + ') </a>'
    child += '<a class="dislike-link" onclick="dislikePost(' + post.postId + ')">Disike (' + numberOfDislikes(post) + ') </a>'
    
    if (username === post.sender) {
        child += '<a onclick="deletePost(' + post.postId + ')">Delete</a>';
    }
    
    if (admin) {
        child += '<a onclick="deleteAccount(\'' + post.sender + '\')">Delete account</a>';
    }

    child += '</li></div>';
    
    return child;
}

function searchByUser() {
    let username = $("#username-searchkey").val();
    let requestUri = '/posts?username=' + username;
    $.getJSON(requestUri, function (posts) {
        $("#dveedlist").empty();

        posts.forEach(function (post) {
            
            var postHTML = constructPostHTML(post);
            $("#dveedlist").prepend(postHTML);
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
            $("#dveedlist").prepend(postHTML);
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
            $("#post-" + id).remove();
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
            let postId = result.postId;
            $("#post-" + postId).find(".like-link").html("Like (" + numberOfLikes(result) + ") ");
        }
    })
}

function dislikePost(id) {
    console.log('disliking post ' + id);
    $.ajax({
        url: '/posts/' + id + '/dislike',
        type: 'post',
        headers: {
            'X-CSRF-TOKEN': getCSRFToken()
        },
        success: function(result) {
            let postId = result.postId;
            $("#post-" + postId).find(".dislike-link").html("Dislike (" + numberOfDislikes(result) + ") ");
        }
    })
}

function createPost() {
    $.ajax({
        url: '/post?content=' + $("#post-content").val() + '&tags=' + $("#post-tags").val(),
        type: 'post',
        headers: {
            'X-CSRF-TOKEN': getCSRFToken()
        },
        success: function(result) {
            console.log('dveed created with an id: ' + result.postId)
        }
    });
}

function numberOfReactions(post, reaction) {
    let likes = 0;
    for (i = 0; i < post.reactions.length; i++) {
        if (post.reactions[i].reactionName === reaction) {
            likes++;
        }
    }
    return likes;
}

function numberOfLikes(post) {
    return numberOfReactions(post, 'like');
}

function numberOfDislikes(post) {
    return numberOfReactions(post, 'dislike');
}

function getCSRFToken() {
    return $('meta[name="_csrf"]').attr('content');
}