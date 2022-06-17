// THIS JAVASCRIPT IS ONLY FOR FETCH REQUESTS

// function limitText() {
//     var elem = $(".limit-text");
//     if (elem.val().length > 10) {
//         elem.val(elem.val().substr(0, 10));
//     }
// }
//
// limitText()

const gamerHaven = 'https://gamerhaven.gg/ajax/'

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
    fetch(gamerHaven + 'like-post', readOption)
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

    fetch(gamerHaven + 'unlike-post', readOption)
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
        if ($(this).hasClass('btn-secondary')) {
            $(this).removeClass('btn-secondary').addClass('btn-primary')

            likePost(array);

            likeText.text(parseInt(likeText.text()) + 1);

        } else if ($(this).hasClass('btn-primary')) {
            $(this).removeClass('btn-primary').addClass('btn-secondary')
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

    fetch(gamerHaven + 'favorite-game', readOption)
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

    fetch(gamerHaven + 'unfavorite-game', readOption)
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
        if ($(this).hasClass('btn-secondary')) {
            $(this).removeClass('btn-secondary').addClass('btn-primary')
            favoriteGame(array);
        } else if ($(this).hasClass('btn-primary')) {
            $(this).removeClass('btn-primary').addClass('btn-secondary')
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

    // fetch(gamerHaven + 'follow-user', readOption)
    fetch(url, readOption)
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

    fetch(gamerHaven + 'unfollow-user', readOption)
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
        if ($(this).hasClass('btn-secondary')) {
            $(this).removeClass('btn-secondary').addClass('btn-primary')
            followUser(array);
        } else if ($(this).hasClass('btn-primary')) {
            $(this).removeClass('btn-primary').addClass('btn-secondary')
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

    fetch(gamerHaven + 'post-comment', readOption)
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

    fetch(gamerHaven + 'delete-comment', readOption)
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
    return `<div><a href="/user/ + ${id}">${user}</a>
                <p>${body}</p></div>`
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
    const clickedId = e.target.dataset.id;
    const commentText = $(`.comment-count[data-id=${clickedId}]`);

    let array = getBtnValue($(this));
    commentText.text(parseInt(commentText.text()) - 1);
    deleteComment(array)
    $(`.dl[data-dl=${e.target.dataset.dl}]`).remove()

});