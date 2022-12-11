let board = null;
initBoard();

function getMouseCoords(e) {
    let cPos = board.getCoordsTopLeftCorner(e),
        absPos = JXG.getPosition(e),
        dx = absPos[0] - cPos[0],
        dy = absPos[1] - cPos[1];

    return new JXG.Coords(JXG.COORDS_BY_SCREEN, [dx, dy], board);
}

function initBoard(radius = null) {
    if (board)
        JXG.JSXGraph.freeBoard(board);

    board = JXG.JSXGraph.initBoard(
        "board",
        { boundingbox: [-6, 6, 6, -6], axis: true, showNavigation: false }
    );

    board.on("down", (e) => {
        if (radius == null)
            return;
        let coords = getMouseCoords(e);
        c1 = coords.usrCoords[1].toFixed(3);
        c2 = coords.usrCoords[2].toFixed(3);

        if (c1 < -4 || c1 > 4 || c2 <= -5 || c2 >= 3) {
            if ($("#bounds_error").length === 0)
                $("#board").after("<p id='bounds_error' style='color: red'>out of bounds</p>");
            return;
        }
        else
            $("#bounds_error").remove();

        board.create('point', [c1, c2],
            {fixed: true, size: 1, name: "A"});
    });
}

function updateRadiusCheck(radius) {
    $("#form\\:check_1")[0].checked = (radius === 1);
    $("#form\\:check_2")[0].checked = (radius === 2);
    $("#form\\:check_3")[0].checked = (radius === 3);
    $("#form\\:check_4")[0].checked = (radius === 4);
    $("#form\\:check_5")[0].checked = (radius === 5);
}

function drawGraph(radius) {
    graphparams = {
        fixed: true,
        vertices: { visible: false },
        strokeWidth: 2,
        fillOpacity: 0,
        borders: {
            strokeWidth: 2,
            strokeOpacity: 0.8,
            fixed: true
        },
        strokeOpacity: 0.8
    };
    board.create('polygon', [[0, 0], [0, radius], [radius, radius], [radius, 0]], graphparams);
    board.create('ellipse', [[0, 0], [0, 0], [radius, 0], 0, -Math.PI / 2], graphparams);
    board.create('polygon', [[0, 0], [0, -radius], [-radius, 0]], graphparams)
}

function handleRedraw(radius) {
    updateRadiusCheck(radius);
    initBoard(radius);
    drawGraph(radius);
}

$("#form\\:check_1").on("click", () => {handleRedraw(1)});
$("#form\\:check_2").on("click", () => {handleRedraw(2)});
$("#form\\:check_3").on("click", () => {handleRedraw(3)});
$("#form\\:check_4").on("click", () => {handleRedraw(4)});
$("#form\\:check_5").on("click", () => {handleRedraw(5)});
