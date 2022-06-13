/////////////  FileStack API   ///////////////////////

const mapImgToDiv = (post) => `<img src="${post.Url}" style="width: 148px; height: 98px">`;
const client = filestack.init(FS_API_KEY);

const options = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    onUploadDone:
        function (res) {
            const url = res.filesUploaded[0].url
            $(".post-image-upload").val(url);
            const postImg = [{Url: url}];
            const postImages = postImg.map(mapImgToDiv)
            $('#img-output').html(postImages)

        }
};

$(".upload-picture").on("click", function () {
    client.picker(options).open()
});


// this is the code to make an api req to RAWG this gets the top 20 games
// const settings = {
//     "async": true,
//     "crossDomain": true,
//     // the token variable must be imported from keys.js THIS IS IMPORTANT!!
//     "url": "https://rawg-video-games-database.p.rapidapi.com/games?key="+ token,
//     "method": "GET",
//     "headers": {
//         "x-rapidapi-key": RAPID_API_TOKEN,
//         "x-rapidapi-host": "rawg-video-games-database.p.rapidapi.com"
//     }
// };
// $.ajax(settings).done(function (response) {
//     console.log(response);
// });

// this is a search function to search for games using the game primary key AKA game_id
// function searcher(GameID) {
//     const search = {
//         "async": true,
//         "crossDomain": true,
//         // the token variable must be imported from keys.js THIS IS IMPORTANT!!
//         "url": "https://rawg-video-games-database.p.rapidapi.com/games/" + GameID + "?key=" + token,
//         "method": "GET",
//         "headers": {
//             "x-rapidapi-key": RAPID_API_TOKEN,
//             "x-rapidapi-host": "rawg-video-games-database.p.rapidapi.com"
//         }
//     };
//     $.ajax(search).done(function (response) {
//         console.log(response);
//     });
// }

// this is a search function that shows a result of 20 games based on a string you enter
// function stringSearch(GameString) {
//     const searchString = {
//         "async": true,
//         "crossDomain": true,
//         // the token variable must be imported from keys.js THIS IS IMPORTANT!!
//         "url": "https://rawg-video-games-database.p.rapidapi.com/games?key=" + token + "&search="+ GameString,
//         "method": "GET",
//         "headers": {
//             "x-rapidapi-key": RAPID_API_TOKEN,
//             "x-rapidapi-host": "rawg-video-games-database.p.rapidapi.com"
//         }
//     };
//     $.ajax(searchString).done(function (response) {
//         console.log(response);
//     });
// }


const mapElementToDiv = (game) => `<div>
                <div>${game.Id}</div>
                <div>${game.Name}</div>
                <div>${game.Description}</div>
                <div><img src="${game.BackgroundImageUrl}"></div>
                <div>${game.Developer}</div>
                <div>${game.Platforms}</div>
<!--                <div>${game.Rating}</div>-->
                <div>${game.Genres}</div>
            </div>`;


const mapElementToForm = (game) => `<form id="myForm" method="post" action="/game">
          <input type="hidden" name="title" value="${game.Name}">
          <input type="hidden" name="background-url" value="${game.BackgroundImageUrl}">
          <input type="hidden" name="description" value="${game.Description}">
          <input type="hidden" name="game-id" value="${game.Id}">
          <input type="hidden" name="developer" value="${game.Developer}">
          <input type="hidden" name="platforms" value="${game.Platforms}">
          <input type="hidden" name="genres" value="${game.Genres}">
    </form>`;


function getPlatforms(array) {

    let platforms = "";
    for (let i = 0; i < array.length; i++) {
        platforms += array[i].platform.name + ", ";
    }
    return platforms;
}

function getGenres(array) {
    let genres = "";
    for (let i = 0; i < array.length; i++) {
        genres += array[i].name + ", ";
    }
    return genres;
}

function searcher(GameID) {
    const search = {
        "async": true,
        "crossDomain": true,
        // the token variable must be imported from keys.js THIS IS IMPORTANT!!
        "url": "https://rawg-video-games-database.p.rapidapi.com/games/" + GameID + "?key=" + token,
        "method": "GET",
        "headers": {
            "x-rapidapi-key": RAPID_API_TOKEN,
            "x-rapidapi-host": "rawg-video-games-database.p.rapidapi.com"
        }
    };
    $.ajax(search).done(function (data) {
        console.log(data);
        const games = [
            {

                Id: data.id,
                Name: data.name,
                Description: data.description_raw,
                BackgroundImageUrl: data.background_image,
                // Rating: data.esrb_rating.name,
                Genres: getGenres(data.genres),
                Developer: data.developers[0].name,
                Platforms: getPlatforms(data.platforms)
            }
        ];

        console.log(games)


        // const test = games.map(mapElementToDiv);
        // $('#testing').html(test);

        const save = games.map(mapElementToForm);
        $('#save').html(save);
        const myForm = document.getElementById("myForm");
        myForm.submit();

    });

}


const mapEleToDiv = (results) => `
            <div class="game-card">
                <div class="card" style="width: 18rem">
                    <img class="card-img-top card-img-top" src="${results.background_image}">
                <div class="card-body card-txt-bottom">
                    <h4 class="game-title" id="game-title" onclick="gameRedirect(this)" data-id="${results.id}">${results.name}</h4>
                </div>
                </div>
            </div>`;


// this function populates the screen based on the searchstring that the user types
function stringSearch(GameString) {
    const searchString = {
        "async": true,
        "crossDomain": true,
        // the token variable must be imported from keys.js THIS IS IMPORTANT!!
        "url": "https://rawg-video-games-database.p.rapidapi.com/games?key=" + token + "&search=" + GameString,
        "method": "GET",
        "headers": {
            "x-rapidapi-key": RAPID_API_TOKEN,
            "x-rapidapi-host": "rawg-video-games-database.p.rapidapi.com"
        }
    };
    $.ajax(searchString).done(function (data) {
        console.log(data);
        const test = data.results.map(mapEleToDiv);
        $('#search-results').html(test);

    });
}


// this function is for the search bar to populate the screen based on there search
//     $('#submit-btn').on('click', function() {
//         var searchQuery = $("#search").val();
//         stringSearch(searchQuery);
//         console.log(searchQuery);
//     });


document.querySelector('#submit-btn').addEventListener('click', function () {
    var searchQuery = $("#search").val();
    window.location = ("/results?search=" + searchQuery);
});

function gameRedirect(elem) {
    // var dataID = $(this).attr("data-id")
    var dataId = $(elem).data("id");
    console.log(dataId)
    // alert(dataId);
    window.location = ("/game?gameID=" + dataId);
}


// function likePost(values){
//     const data = {
//         user_id: values[1],
//         post_id: values[0]
//     }
//     const url = 'http://localhost:8080/ajax/like-post';
//     const readOption = {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(data),
//     };
//
//     fetch(url, readOption)
//         .then((res) => res.json())
//         .then((data) => {
//             console.log(data);
//         })
//         .catch((e) => {
//             console.log('Error!!', e)
//         });
// }
//
//
// function unlikePost(values){
//     const data = {
//         user_id: values[1],
//         post_id: values[0]
//     }
//     const url = 'http://localhost:8080/ajax/unlike-post';
//     const readOption = {
//         method: 'POST',
//         headers: {
//             'Content-Type': 'application/json',
//         },
//         body: JSON.stringify(data),
//     };
//
//     fetch(url, readOption)
//         .then((res) => res.json())
//         .then((data) => {
//             console.log(data);
//         })
//         .catch((e) => {
//             console.log('Error!!', e)
//         });
// }
//
// function getBtnValue(target){
//     return target[0].attributes[0].value.split(',');
// }
//
//
// $('.like-btn').on('click', function (e) {
//     e.preventDefault();
//     let array = getBtnValue($(this));
//
//     if (array[1] !== -1) {
//         if ($(this).hasClass('btn-secondary')) {
//             $(this).removeClass('btn-secondary').addClass('btn-primary')
//             likePost(array);
//         } else if ($(this).hasClass('btn-primary')) {
//             $(this).removeClass('btn-primary').addClass('btn-secondary')
//             unlikePost(array)
//             }
//     }
// });
//
// // redirect view to user profile on click
// $(".view-profile").on("click", function (){
//     let userId = $(this).attr("data-id")
//     window.location = "/user/" + userId
// });


// redirect view to edit post =>
$(".edit").on("click", function () {
    let postId = $(this).attr("data-id")
    window.location = "/post/" + postId
});

function redirectToLogin() {
    document.location.href = "http://localhost:8080/login"
}