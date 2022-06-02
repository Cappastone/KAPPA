
// this is the code to make an api req to RAWG this gets the top 20 games
const settings = {
    "async": true,
    "crossDomain": true,
    // the token variable must be imported from keys.js THIS IS IMPORTANT!!
    "url": "https://rawg-video-games-database.p.rapidapi.com/games?key="+ token,
    "method": "GET",
    "headers": {
        "x-rapidapi-key": RAPID_API_TOKEN,
        "x-rapidapi-host": "rawg-video-games-database.p.rapidapi.com"
    }
};
$.ajax(settings).done(function (response) {
    console.log(response);
});

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
    $.ajax(searchString).done(function (response) {
        console.log(response);
    });
}


const mapElementToDiv = (game) => `<div>
                <div>${game.Name}</div>
                <div>${game.Description}</div>
                <div><img src="${game.BackgroundImageUrl}"></div>
            </div>`;

const mapElementToForm = (game) => `<form method="post" action="/games">
                <input type="hidden" name="title" value="${game.Name}">
                <input type="hidden" name="description" value="${game.Description}">
                <input type="hidden" name="background-url" value="${game.BackgroundImageUrl}">
                <button></button>
            </form>`;




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
            Name: data.name,
            Description: data.description,
            BackgroundImageUrl: data.background_image
            }
        ];


        const test = games.map(mapElementToDiv);
        $('#testing').html(test);

    });

}


