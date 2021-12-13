let mapArray, ctx, currentImgMainX, currentImgMainY;
let imgMountain, imgMain, imgEnemy;
let targetImgMainX, targetImgMainY, targetBlockX, targetBlockY;
let temp = 0;
let temp1 = 0;

$(document).ready(function () {
  //0:可走,1:障礙,2:終點,3:敵人
  mapArray = [
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
    [1, 1, 1, 8, 9, 10, 1, 1, 1, 15, 16, 17],
    [2, 4, 5, 0, 1, 0, 1, 13, 14, 0, 1, 1],
    [1, 1, 6, 7, 1, 11, 12, 0, 1, 1, 1, 1],
  ];
  ctx = $("#myCanvas")[0].getContext("2d");
  imgMain = new Image();
  imgMain.src = "./images/tuntun/stand.png";
  currentImgMainX = 0;
  currentImgMainY = 500;
  imgMain.onload = function () {
    ctx.drawImage(
      imgMain,
      0,
      0,
      100,
      100,
      currentImgMainX,
      currentImgMainY,
      100,
      100
    );
  };

  let introduce = $(".typing");
  setTimeout(() => introduce.addClass("animate"), 1);
});

$(document).keydown(function (event) {
  // let targetImgMainX, targetImgMainY, targetBlockX, targetBlockY;

  event.preventDefault();
  // console.log(event.code);
  switch (event.code) {
    case "ArrowLeft":
      targetImgMainX = currentImgMainX - 100;
      targetImgMainY = currentImgMainY;
      if(temp1 == 0){
        // console.log(temp)
        imgMain.src = "./images/tuntun/walk-l01.png";
        temp1 = 1;
      }
      else{
        // console.log(temp)
        imgMain.src = "./images/tuntun/walk-l02.png";
        temp1 = 0;
      }
      break;
    case "ArrowUp":
      targetImgMainX = currentImgMainX;
      targetImgMainY = currentImgMainY - 100;
      imgMain.src = "./images/tuntun/back.png";
      break;
    case "ArrowRight":
      targetImgMainX = currentImgMainX + 100;
      targetImgMainY = currentImgMainY;
      if(temp == 0){
        // console.log(temp)
        imgMain.src = "./images/tuntun/walk1.png";
        temp = 1;
      }
      else{
        // console.log(temp)
        imgMain.src = "./images/tuntun/walk2.png";
        temp = 0;
      }
      break;
    case "ArrowDown":
      targetImgMainX = currentImgMainX;
      targetImgMainY = currentImgMainY + 100;
      imgMain.src = "./images/tuntun/stand.png"
      break;
    default:
      return;
  }
  if (
    targetImgMainX <= 1100 &&
    targetImgMainX >= 0 &&
    targetImgMainY <= 600 &&
    targetImgMainY >= 0
  ) {
    targetBlockX = targetImgMainX / 100;
    targetBlockY = targetImgMainY / 100;
  } else {
    targetBlockX = -1;
    targetBlockY = -1;
  }

  ctx.clearRect(currentImgMainX, currentImgMainY, 100, 100);

  if (
    (targetBlockX == -1 && targetBlockY == -1) ||
    mapArray[targetBlockY][targetBlockX] == 1 ||
    mapArray[targetBlockY][targetBlockX] == 3
  ) {
  } else {
    $("#introduce").empty();
    currentImgMainX = targetImgMainX;
    currentImgMainY = targetImgMainY;
  }
  ctx.drawImage(
    imgMain,
    0,
    0,
    2000,
    2000,
    currentImgMainX,
    currentImgMainY,
    100,
    100
  );

  //===========================

  let introduce = $(".typing");
  if (mapArray[targetBlockY][targetBlockX] == 0) {
    introduce.removeClass("animate");
    introduce.removeClass("animate1");
    introduce.removeClass("animate2");
    introduce.removeClass("animate3");
    introduce.removeClass("animate4");
    introduce.removeClass("animate5");
    introduce.removeClass("animate6");
    introduce.removeClass("animate7");
    introduce.removeClass("animate9");
    introduce.removeClass("animate10");
    introduce.removeClass("animate11");
    introduce.removeClass("animate12");

    setTimeout(() => introduce.addClass("animate0"), 1);
  }
  if (mapArray[targetBlockY][targetBlockX] == 2) {
    introduce.removeClass("animate");
    introduce.removeClass("animate1");
    setTimeout(() => introduce.addClass("animate"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 4) {
    introduce.removeClass("animate");
    introduce.removeClass("animate1");
    introduce.removeClass("animate2");
    setTimeout(() => introduce.addClass("animate1"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 5) {
    introduce.removeClass("animate1");
    introduce.removeClass("animate3");
    introduce.removeClass("animate4");
    introduce.removeClass("animate5");
    setTimeout(() => introduce.addClass("animate2"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 6) {
    introduce.removeClass("animate2");
    introduce.removeClass("animate4");
    setTimeout(() => introduce.addClass("animate3"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 7) {
    introduce.removeClass("animate2");
    introduce.removeClass("animate3");
    introduce.removeClass("animate5");
    setTimeout(() => introduce.addClass("animate4"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 8) {
    introduce.removeClass("animate2");
    introduce.removeClass("animate4");
    introduce.removeClass("animate6");
    setTimeout(() => introduce.addClass("animate5"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 9) {
    introduce.removeClass("animate5");
    introduce.removeClass("animate7");
    setTimeout(() => introduce.addClass("animate6"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 10) {
    introduce.removeClass("animate6");
    introduce.removeClass("animate8");
    setTimeout(() => introduce.addClass("animate7"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 11) {
    introduce.removeClass("animate7");
    introduce.removeClass("animate9");
    setTimeout(() => introduce.addClass("animate8"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 12) {
    introduce.removeClass("animate8");
    introduce.removeClass("animate10");
    setTimeout(() => introduce.addClass("animate9"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 13) {
    introduce.removeClass("animate9");
    introduce.removeClass("animate11");
    setTimeout(() => introduce.addClass("animate10"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 14) {
    introduce.removeClass("animate10");
    introduce.removeClass("animate12");
    setTimeout(() => introduce.addClass("animate11"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 15) {
    introduce.removeClass("animate11");
    introduce.removeClass("animate13");
    setTimeout(() => introduce.addClass("animate12"), 1);
  }

  if (mapArray[targetBlockY][targetBlockX] == 16) {
    introduce.removeClass("animate12");
    introduce.removeClass("animate14");
    setTimeout(() => introduce.addClass("animate13"), 1);
  }
  if (mapArray[targetBlockY][targetBlockX] == 17) {
    introduce.removeClass("animate13");
    // introduce.removeClass('animate4');
    setTimeout(() => introduce.addClass("animate14"), 1);
    setTimeout(
      () =>
        Swal.fire({
          title: "加入 Escape ME !",
          imageUrl: "./images/tuntun/love.png",
          imageWidth: 180,
          imageHeight: 180,
          showCancelButton: true,
          confirmButtonText: "Join Now !",
          cancelButtonText: "Later...",
        }).then((result) => {
          if (result.isConfirmed) {
            location = "./signup.html";
          }
        }),
      3500
    );
  }
});
