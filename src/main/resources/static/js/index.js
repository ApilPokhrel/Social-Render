




var limit = 1;
var skip = 0;
function allHacks() {
console.log('limit is '+ limit);
console.log('skip is '+ skip);

    var allPost = [];

    var comparing = [];
    var statics = [];

    axios({
        method: 'get',
        url: 'http://' + window.location.hostname + ':5000/api/allfeed',
        params:{
            limit: limit,
            skip: skip
        }
    }).then(function (res) {




        res.data[0].forEach(function (d) {

            if (d[1].is == 'comparision') {
                comparing.push(d);
            }
            else {
                statics.push(d);
            }
        });


        var lnt = (comparing.length > statics.length) ? comparing.length : statics.length;

        for (var l = 0; l < lnt; l++) {

            allPost.push(comparing[l]);
            allPost.push(statics[l]);

        }


        var allPost2 = allPost.filter(function (n) {
            return n != undefined;
        });

        // allPost2 = allPost2.reverse();
        console.log(allPost2);

        for (var i = 0; i < allPost2.length; i++) {

            let options;

            if (allPost2[i][1].is == 'comparision') {

                options = {
                    comp: true,
                    is: allPost2[i][1].is,
                    postid: allPost2[i][1].id,
                    userImage: allPost2[i][0].image,
                    userName: allPost2[i][0].name,
                    userSlug: allPost2[i][0].slug,
                    file1: allPost2[i][1].file1,
                    file2: allPost2[i][1].file2,
                    owntag: allPost2[i][1].owntag,
                    like1: (allPost2[i][1].f1likes.includes(res.data[1].slug) ? "brown" : "black"),
                    like2: (allPost2[i][1].f2likes.includes(res.data[1].slug) ? "brown" : "black"),
                    brandtag: allPost2[i][1].brandtag,
                    title: allPost2[i][1].tittle,
                    createdAt: allPost2[i][1].createdAt,
                    f1likes: allPost2[i][1].f1likes.length,
                    f2likes: allPost2[i][1].f2likes.length,
                    file1img: (allPost2[i][1].file1type == 'image' ? true : null),
                    file2img: (allPost2[i][1].file2type == 'image' ? true : null),
                    opinion1: allPost2[i][1].opinions[0].tagvalue,
                    opinion2: allPost2[i][1].opinions[1].tagvalue,
                    opinion3: allPost2[i][1].opinions[2].tagvalue,
                    opinion4: allPost2[i][1].opinions[3].tagvalue,
                    opinion5: allPost2[i][1].opinions[4].tagvalue,
                    url: allPost2[i][1].url1,
                    subtag: allPost2[i][1].subtag,
                    i: i,
                    opn1A:( allPost2[i][1].opinions[0].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn1B:( allPost2[i][1].opinions[0].f2.includes(res.data[1].slug) ? "fas" : "far"),
                    opn2A:( allPost2[i][1].opinions[1].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn2B:( allPost2[i][1].opinions[1].f2.includes(res.data[1].slug) ? "fas" : "far"),
                    opn3A:( allPost2[i][1].opinions[2].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn3B:( allPost2[i][1].opinions[2].f2.includes(res.data[1].slug) ? "fas" : "far"),
                    opn4B:( allPost2[i][1].opinions[3].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn4A:( allPost2[i][1].opinions[3].f2.includes(res.data[1].slug) ? "fas" : "far"),
                    opn5A:( allPost2[i][1].opinions[4].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn5B:( allPost2[i][1].opinions[4].f2.includes(res.data[1].slug) ? "fas" : "far"),
                    opn1Al: allPost2[i][1].opinions[0].f1.length,
                    opn1Bl: allPost2[i][1].opinions[0].f2.length,
                    opn2Al: allPost2[i][1].opinions[1].f1.length,
                    opn2Bl: allPost2[i][1].opinions[1].f2.length,
                    opn3Al: allPost2[i][1].opinions[2].f1.length,
                    opn3Bl: allPost2[i][1].opinions[2].f2.length,
                    opn4Bl: allPost2[i][1].opinions[3].f1.length,
                    opn4Al: allPost2[i][1].opinions[3].f2.length,
                    opn5Al: allPost2[i][1].opinions[4].f1.length,
                    opn5Bl: allPost2[i][1].opinions[4].f2.length,
                    o1: 0,
                    o2: 1,
                    o3: 2,
                    o4: 3,
                    o5: 4,
                };
            }

            else {

                options = {
                    comp: null,
                    is: allPost2[i][1].is,
                    postid: allPost2[i][1].id,
                    userImage: allPost2[i][0].image,
                    userName: allPost2[i][0].name,
                    userSlug: allPost2[i][0].slug,
                    file: allPost2[i][1].file[0].file,
                    owntag: allPost2[i][1].owntag,
                    brandtag: allPost2[i][1].brandtag,
                    title: allPost2[i][1].tittle,
                    createdAt: allPost2[i][1].createdAt,
                    flikes: allPost2[i][1].filelikes.length,
                    like: (allPost2[i][1].filelikes.includes(res.data[1].slug) ? "brown" : "black"),
                    fimg: (allPost2[i][1].file[0].filetype == 'image' ? true: null),
                    opinion1: allPost2[i][1].opinions[0].tagvalue,
                    opinion2: allPost2[i][1].opinions[1].tagvalue,
                    opinion3: allPost2[i][1].opinions[2].tagvalue,
                    opinion4: allPost2[i][1].opinions[3].tagvalue,
                    opinion5: allPost2[i][1].opinions[4].tagvalue,

                    url: allPost2[i][1].url1,
                    subtag: allPost2[i][1].subtag,
                    opn1l: allPost2[i][1].opinions[0].f1.length,
                    opn2l: allPost2[i][1].opinions[1].f1.length,
                    opn3l: allPost2[i][1].opinions[2].f1.length,
                    opn4l: allPost2[i][1].opinions[3].f1.length,
                    opn5l: allPost2[i][1].opinions[4].f1.length,

                    opn1: ( allPost2[i][1].opinions[0].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn2: ( allPost2[i][1].opinions[1].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn3: ( allPost2[i][1].opinions[2].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn4: ( allPost2[i][1].opinions[3].f1.includes(res.data[1].slug) ? "fas" : "far"),
                    opn5: ( allPost2[i][1].opinions[4].f1.includes(res.data[1].slug) ? "fas" : "far"),

                    i: i,
                    o1: 0,
                    o2: 1,
                    o3: 2,
                    o4: 3,
                    o5: 4,
                };
            }

            var source = jQuery('#data-template').html();
            var compiled = dust.compile(source, "intro");
            dust.loadSource(compiled);

            dust.render("intro", options, function (err, out) {
                if (err) {
                    console.log("error in dust is ", err);
                }
                // jQuery('#dataDisplay').append(out);
                $( ".mcla").append(out);
            });
        }


        skip++;

    }).catch(function (err) {
        console.log('error occured ', err);
    });

    window.scrollTo(0,document.body.scrollHeight);

};


allHacks();


document.getElementById('loadMore').addEventListener('click', function () {
    // document.getElementById('dataDisplay').innerHTML = '';

    allHacks();
    jQuery('#mainDisplay').append()


});
