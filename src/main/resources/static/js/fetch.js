// THIS JAVASCRIPT IS ONLY FOR FETCH REQUESTS


//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////


const gamerHaven = 'https://gamerhaven.gg/ajax/'
const localHost = 'http://localhost:8080/ajax/'


/////////////////////////////   MESSAGE TO JOHN        /////////////////////////////
//////////////////////////// MESSAGE TO OSCAR TY //////////////////////////

/////////////////////////////   Change function here   /////////////////////////////


function changeUrls() {
    return gamerHaven;
    // return localHost;
}


//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////////////////////////////////////////////////////////////


/////////////////////////////  Helper functions  /////////////////////////////////

function getBtnValue(target) {
    return target[0].attributes[0].value.split(',');
}

/////////////////////////////  Like Post Functionality  /////////////////////////////////


function likePost(values) {
    const data = {
        user_id: values[1],
        post_id: values[0]
    }
    const url = 'http://localhost:8080/ajax/like-post';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };
    fetch(changeUrls() + 'like-post', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


function unlikePost(values) {
    const data = {
        user_id: values[1],
        post_id: values[0]
    }
    const url = 'http://localhost:8080/ajax/unlike-post';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'unlike-post', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}

// const postId= $('.like-count')[0].dataset.ps;
// const likeCount = $('.like-count')[0].dataset.id;
// console.log(likeCount);
// console.log(postId);


$('.like-btn').on('click', function (e) {
    e.preventDefault();
    let array = getBtnValue($(this));
    console.log(array)
    const clickedId = e.target.dataset.id;

    const likeText = $(`.like-count[data-id=${clickedId}]`);

    if (array[1] !== -1) {
        if ($(this).hasClass('btn-outline-light')) {
            $(this).removeClass('btn-outline-light').addClass('btn-purple')

            likePost(array);

            likeText.text(parseInt(likeText.text()) + 1);

        } else if ($(this).hasClass('btn-purple')) {
            $(this).removeClass('btn-purple').addClass('btn-outline-light')
            unlikePost(array)
            likeText.text(parseInt(likeText.text()) - 1);
        }
    }
});


/////////////////////////////  Favorite Game Functionality  /////////////////////////////////


function favoriteGame(values) {
    const data = {
        user_id: values[1],
        game_id: values[0]
    }
    const url = 'http://localhost:8080/ajax/favorite-game';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'favorite-game', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


function unFavoriteGame(values) {
    const data = {
        user_id: values[1],
        game_id: values[0]
    }
    const url = 'http://localhost:8080/ajax/unfavorite-game';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'unfavorite-game', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


$('.fav-btn').on('click', function (e) {
    e.preventDefault();
    let array = getBtnValue($(this));

    if (array[1] !== -1) {
        if ($(this).hasClass('btn-outline-light')) {
            $(this).removeClass('btn-outline-light').addClass('btn-purple')
            favoriteGame(array);
        } else if ($(this).hasClass('btn-purple')) {
            $(this).removeClass('btn-purple').addClass('btn-outline-light')
            unFavoriteGame(array)
        }
    }
});


/////////////////////////////  Follow User Functionality  /////////////////////////////////


function followUser(values) {

    const data = {
        user_id: values[0],
        follower_id: values[1]
    }
    const url = 'http://localhost:8080/ajax/follow-user';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'follow-user', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


function unFollowUser(values) {
    const data = {
        user_id: values[0],
        follower_id: values[1]
    }
    const url = 'http://localhost:8080/ajax/unfollow-user';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'unfollow-user', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


$('.follow-btn').on('click', function (e) {
    e.preventDefault();
    let array = getBtnValue($(this));

    if (array[1] !== -1) {
        if ($(this).hasClass('btn-outline-light')) {
            $(this).removeClass('btn-outline-light').addClass('btn-purple')
            $(this).text('Following')
            followUser(array);
        } else if ($(this).hasClass('btn-purple')) {
            $(this).removeClass('btn-purple').addClass('btn-outline-light')
            $(this).text('Follow')
            unFollowUser(array)
        }
    }
});

/////////////////////////////  Post Comment Functionality  /////////////////////////////////

function postComment(values) {

    const data = {
        user_id: values[0],
        post_id: values[1],
        comment_data: values[2]
    }
    const url = 'http://localhost:8080/ajax/post-comment';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'post-comment', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


function deleteComment(values) {
    const data = {
        comment_id: values[0]
    }
    const url = 'http://localhost:8080/ajax/delete-comment';
    const readOption = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(data),
    };

    fetch(changeUrls() + 'delete-comment', readOption)
        // fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


const addComment = (id, user, body) => {
    return `<div>
          <a class="profile-name-c" href="/user/ + ${id}">${user}</a>
          <p>${body}</p>
          </div>`
}


$('.post-comment-btn').on('click', function (e) {
    e.preventDefault();

    const clickedId = e.target.dataset.id;

    const commentText = $(`.comment-count[data-id=${clickedId}]`);
    console.log(e.target.dataset.id);
    let array = getBtnValue($(this));
    const comment = $(`.comment-body[data-id=${e.target.dataset.id}]`)
    const newArray = [array[0], array[1], comment.val()]
    const username = $('#username').text()

    commentText.text(parseInt(commentText.text()) + 1);
    postComment(newArray);

    $(`.comments-container[data-id=${e.target.dataset.id}]`).append(addComment(array[0], username, comment.val()));
    comment.val("")
});


// $('.delete-comment-btn').on('click', function (e) {
//     e.preventDefault();
//     let array = getBtnValue($(this));
//     deleteComment(array);
//
// });


$('.delete').on('click', function (e) {
    e.preventDefault();
    var check = confirm("Are you sure you want to delete this comment?")
    if (check === true) {
        const clickedId = e.target.dataset.id;
        const commentText = $(`.comment-count[data-id=${clickedId}]`);
        let array = getBtnValue($(this));
        commentText.text(parseInt(commentText.text()) - 1);
        deleteComment(array)
        $(`.dl[data-dl=${e.target.dataset.dl}]`).remove()
    }

});