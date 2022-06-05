
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
                <div>${game.Rating}</div>
                <div>${game.Genres}</div>
            </div>`;

const mapElementToForm = (game) => `<form method="post" action="/games">
                <input type="hidden" name="title" value="${game.Name}">
                <input type="hidden" name="description" value="${game.Description}">
                <input type="hidden" name="background-url" value="${game.BackgroundImageUrl}">
                <input type="hidden" name="game-id" value="${game.Id}">
                <input type="hidden" name="developer" value="${game.Developer}">
                <input type="hidden" name="platforms" value="${game.Platforms}">
                <input type="hidden" name="rating" value="${game.Rating}">
                <input type="hidden" name="genres" value="${game.Genres}">
                <button>Save To Database</button>
            </form>`;


function getPlatforms (array) {
    let platforms = "";
    for (let i = 0; i < array.length; i++) {
        platforms += array[i].platform.name + ", ";
    }
    return platforms;
}

function getGenres (array) {
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
            Description: data.description,
            BackgroundImageUrl: data.background_image,
            Rating: data.esrb_rating.name,
            Genres: getGenres(data.genres),
            Developer: data.developers[0].name,
            Platforms: getPlatforms(data.platforms)

            }
        ];

        console.log(games)


        const test = games.map(mapElementToDiv);
        $('#testing').html(test);

        const save = games.map(mapElementToForm);
        $('#save').html(save);

    });

}





const mapEleToDiv = (results) => `
            <div class="game-card">
                <div class="card" style="width: 18rem">
                    <img class="card-img-top card-img-top" src="${results.background_image}">
                <div class="card-body card-txt-bottom">
                    <p>${results.name}</p>
                </div>
                </div>
            </div>`;


// this function populates the screen based on the searchstring that the user types
function stringSearch(GameString) {
    const searchString = {
        "async": true,
        "crossDomain": true,
        // the token variable must be imported from keys.js THIS IS IMPORTANT!!
        "url": "https://rawg-video-games-database.p.rapidapi.com/games?key=" + token + "&search="+ GameString,
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
$("#submit").click( function() {
    var searchQuery = $("#search").val();
    stringSearch(searchQuery);
    console.log(searchQuery);
});