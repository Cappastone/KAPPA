// THIS JAVASCRIPT IS ONLY FOR FETCH REQUESTS


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

    fetch(url, readOption)
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

    fetch(url, readOption)
        .then((res) => res.json())
        .then((data) => {
            console.log(data);
        })
        .catch((e) => {
            console.log('Error!!', e)
        });
}


$('.like-btn').on('click', function (e) {
    e.preventDefault();
    let array = getBtnValue($(this));

    if (array[1] !== -1) {
        if ($(this).hasClass('btn-secondary')) {
            $(this).removeClass('btn-secondary').addClass('btn-primary')
            likePost(array);
        } else if ($(this).hasClass('btn-primary')) {
            $(this).removeClass('btn-primary').addClass('btn-secondary')
            unlikePost(array)
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

    fetch(url, readOption)
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

    fetch(url, readOption)
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

    fetch(url, readOption)
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