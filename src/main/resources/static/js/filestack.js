/////////////  FileStack API   ///////////////////////

const mapImgToDiv = (post) => `<img src="${post.Url}" style="object-fit: cover; width: 250px; height: 250px; padding-left: 20px; padding-right: 20px;">`;
const client = filestack.init(FS_API_TOKEN);

function upload (res) {
    const url = res.filesUploaded[0].url
    const postImg = [{Url: url}]
    const postImages = postImg.map(mapImgToDiv)
    $(".post-image-upload").val([url]);
    return $('#img-output').html(postImages)
}

function upload2 (res) {
    const url = res.filesUploaded[0].url
    const url2 = res.filesUploaded[1].url
    const postImg = [{Url: url}, {Url: url2}];
    const postImages = postImg.map(mapImgToDiv)
    $(".post-image-upload").val([url, url2]);
    return $('#img-output').html(postImages)
}

function upload3 (res) {
    const url = res.filesUploaded[0].url
    const url2 = res.filesUploaded[1].url
    const url3 = res.filesUploaded[2].url
    const postImg = [{Url: url}, {Url: url2}, {Url: url3}];
    const postImages = postImg.map(mapImgToDiv)
    $(".post-image-upload").val([url, url2, url3]);
    return $('#img-output').html(postImages)
}

const options = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    maxFiles: 3,
    onUploadDone:
        function (res) {
            if (res.filesUploaded.length === 1) {
                upload(res)
            } else if (res.filesUploaded.length === 2) {
                upload2(res)
            } else if (res.filesUploaded.length === 3) {
                upload3(res)
            }
        }
};

const options2 = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    onUploadDone:
        function (res) {
            const url = res.filesUploaded[0].url
            $(".change-profile-pic").val(url);
            $('#profile-pic').submit()
        }
};

const options3 = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    onUploadDone:
        function (res) {
            const url = res.filesUploaded[0].url
            $(".change-banner").val(url);
            $('#banner').submit()
        }
};

$(".upload-picture").on("click", function () {
    client.picker(options).open()
});

$(".change-profile-pic-btn").on("click", function () {
    client.picker(options2).open()
});

$(".change-banner-btn").on("click", function () {
    client.picker(options3).open()
});



const options4 = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    maxFiles: 3,
    onUploadDone:

        function (res) {
            if (res.filesUploaded.length === 1) {
                $("#img-urls").val(res.filesUploaded[0].url);
                $('#upload-post-images').submit()
            }
            if (res.filesUploaded.length === 2) {
                const urls = [res.filesUploaded[0].url, res.filesUploaded[1].url]
                $("#img-urls").val(urls);
                $('#upload-post-images').submit()
            }
            if (res.filesUploaded.length === 3) {
                const urls = [res.filesUploaded[0].url, res.filesUploaded[1].url, res.filesUploaded[2].url]
                $("#img-urls").val(urls);
                $('#upload-post-images').submit()
            }
        }
};
$("#upload-post-img-btn-zero").on("click", function () {
    client.picker(options4).open()
});


const options5 = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    maxFiles: 2,
    onUploadDone:
        function (res) {
            if (res.filesUploaded.length === 1) {
                $("#img-urls").val(res.filesUploaded[0].url);
                $('#upload-post-images').submit()
            }
            if (res.filesUploaded.length === 2) {
                const urls = [res.filesUploaded[0].url, res.filesUploaded[1].url]
                $("#img-urls").val(urls);
                $('#upload-post-images').submit()
            }
        }
};

$("#upload-post-img-btn-one").on("click", function () {
    client.picker(options5).open()
});

const options6 = {
    fromSources: ["local_file_system", "instagram", "facebook"],
    maxFiles: 1,
    onUploadDone:
        function (res) {
            if (res.filesUploaded.length === 1) {
                const url = res.filesUploaded[0].url
                $("#img-urls").val(url);
                $('#upload-post-images').submit()
            }
        }
};
$("#upload-post-img-btn-two").on("click", function () {
    client.picker(options6).open()
});