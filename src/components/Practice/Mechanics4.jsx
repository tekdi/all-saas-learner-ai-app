import { Box } from "@mui/material";
import React, { useEffect, useState } from "react";
import VoiceAnalyser from "../../utils/VoiceAnalyser";
import MainLayout from "../Layouts.jsx/MainLayout";
import correctSound from "../../assets/audio/correct.wav";
import wrongSound from "../../assets/audio/wrong.wav";
import addSound from "../../assets/audio/add.mp3";
import removeSound from "../../assets/audio/remove.wav";
import PropTypes from "prop-types";

const Mechanics4 = ({
  page,
  setPage,
  setVoiceText,
  setVoiceAnimate,
  type,
  handleNext,
  background,
  header,
  parentWords = "",
  image,
  enableNext,
  showTimer,
  points,
  steps,
  currentStep,
  contentId,
  contentType,
  level,
  isDiscover,
  progressData,
  showProgress,
  playTeacherAudio = () => {},
  callUpdateLearner,
  disableScreen,
  isShowCase,
  handleBack,
  setEnableNext,
  loading,
  setOpenMessageDialog,
  audio,
}) => {
  const [words, setWords] = useState(
    type === "word" ? [] : ["Friend", "She is", "My"]
  );
  const [wordsAfterSplit, setWordsAfterSplit] = useState([]);

  useEffect(() => {
    setSelectedWords([]);
  }, [contentId]);

  const [shake, setShake] = useState(false);

  function jumbleSentence(sentence) {
    // Split the sentence into words
    const words = sentence.split(" ");

    // Shuffle the words using Fisher-Yates (Durstenfeld) shuffle algorithm
    for (let i = words.length - 1; i > 0; i--) {
      // Pick a random index from 0 to i
      const j = Math.floor(Math.random() * (i + 1));

      // Swap words[i] with the element at random index
      [words[i], words[j]] = [words[j], words[i]];
    }

    // Join the jumbled words back into a sentence
    return words;
  }

  useEffect(() => {
    let wordsArr = jumbleSentence(parentWords);
    if (parentWords) {
      for (let i = wordsArr.length - 1; i > 0; i--) {
        let j = Math.floor(Math.random() * i);
        let k = wordsArr[i];
        wordsArr[i] = wordsArr[j];
        wordsArr[j] = k;
      }
      setWordsAfterSplit(wordsArr);
      setWords(wordsArr);
    }
  }, [parentWords]);

  const [selectedWords, setSelectedWords] = useState([]);

  const handleWords = (word, isSelected) => {
    setShake(true);
    setTimeout(() => {
      setShake(false);
    }, 3000);

    if (selectedWords?.length + 1 !== wordsAfterSplit?.length || isSelected) {
      let audio = new Audio(isSelected ? removeSound : addSound);
      audio.play();
      setEnableNext(false);
    }

    if (isSelected) {
      let selectedWordsArr = [...selectedWords];
      let index = selectedWordsArr?.findIndex((elem) => elem === word);
      selectedWordsArr?.splice(index, 1);
      setSelectedWords(selectedWordsArr);
      setWords([...words, word]);
    } else {
      let wordsArr = [...words];
      let index = wordsArr?.findIndex((elem) => elem === word);
      wordsArr?.splice(index, 1);
      setWords(wordsArr);
      setSelectedWords([...selectedWords, word]);
      if (selectedWords?.length + 1 === wordsAfterSplit?.length) {
        let audio = new Audio(
          [...selectedWords, word]?.join(" ") === parentWords
            ? correctSound
            : wrongSound
        );
        audio.play();
      }
    }
  };

  const determineAnswer = () => {
    if (selectedWords?.length !== wordsAfterSplit?.length) {
      return "";
    }
    return selectedWords?.join(" ") === parentWords ? "correct" : "wrong";
  };

  const answer = determineAnswer();

  const getClassName = (answer, shake) => {
    if (answer === "wrong") {
      return `audioSelectedWrongWord ${shake ? "shakeImage" : ""}`;
    }
    return "";
  };

  const getStyles = (answer, type, words, selectedWords) => {
    const border = (() => {
      if (answer !== "wrong") {
        if (answer === "correct") return "none";
        if (!words.length && selectedWords.length && type === "word")
          return "2px solid #1897DE"; // Blue border for specific condition
        return "2px solid rgba(51, 63, 97, 0.15)";
      }
      return ""; // Default light border
    })();

    const color = (() => {
      if (type === "word") {
        if (answer === "correct") return "#58CC02";
        if (answer === "wrong") return "#C30303";
        return "#1897DE"; // Default color
      }
      return answer === "wrong" ? "#C30303" : "#333F61";
    })();

    const marginLeft = type === "word" || answer === "correct" ? 0 : "20px";

    return {
      borderRadius: "12px",
      padding: "5px 10px",
      border,
      color,
      fontWeight: type === "word" ? 600 : 700,
      fontSize: "clamp(1.5rem, 3.5vw, 2.5rem)",
      fontFamily: "Quicksand",
      cursor: "pointer",
      backgroundColor: "white",
      marginLeft,
    };
  };

  const getBorderColor = (answer, words, selectedWords, type) => {
    if (answer === "correct") {
      return "#58CC02";
    } else if (answer === "wrong") {
      return "#C30303";
    } else if (!words?.length && !!selectedWords?.length && type === "word") {
      return "#1897DE";
    }
    return "rgba(51, 63, 97, 0.10)";
  };

  return (
    <MainLayout
      background={background}
      handleNext={handleNext}
      enableNext={enableNext}
      showTimer={showTimer}
      points={points}
      pageName={"m4"}
      {...{
        steps,
        currentStep,
        level,
        progressData,
        showProgress,
        playTeacherAudio,
        handleBack,
        disableScreen,
        loading,
      }}
    >
      <div
        style={{
          left: `calc(50% - 258px / 2)`,
          top: `calc(50% - 45px / 2 - 235.5px)`,
          fontFamily: "Quicksand",
          fontStyle: "normal",
          fontWeight: 600,
          fontSize: "36px",
          lineHeight: "45px",
          alignItems: "center",
          textAlign: "center",
          color: "#333F61",
        }}
      >
        {header}
      </div>
      <Box sx={{ display: "flex", justifyContent: "center", mb: 2, mt: 8 }}>
        <Box
          sx={{
            minWidth: "250px",
            minHeight: "75px",
            display: "flex",
            justifyContent: "center",
            flexWrap: "wrap",
            alignItems: "center",
            borderRadius: "15px",
            border: `2px solid ${getBorderColor(
              answer,
              words,
              selectedWords,
              type
            )}`,
            cursor: "pointer",
            letterSpacing: answer != "correct" ? "5px" : "normal",
            background: "#FBFBFB",
            paddingX: type === "word" ? 0 : "20px",
          }}
        >
          {selectedWords?.map((elem) => {
            const className = getClassName(answer, shake);
            const style = getStyles(answer, type, words, selectedWords);

            return (
              <button
                key={elem}
                onClick={() => handleWords(elem, true)}
                onKeyDown={(e) => e.key === "Enter" && handleWords(elem, true)}
                className={className}
                style={style}
                type="button"
              >
                {elem}
              </button>
            );
          })}
        </Box>
      </Box>
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          flexWrap: "wrap",
          mb: 3,
        }}
      >
        {words?.map((elem) => (
          <React.Fragment key={elem}>
            {type === "word" ? (
              <Box
                onClick={() => handleWords(elem)}
                sx={{
                  display: "flex",
                  justifyContent: "center",
                  alignItems: "center",
                  height: { xs: "50px", sm: "60px", md: "70px" },
                  minWidth: { xs: "50px", sm: "60px", md: "70px" },
                  background: "#1897DE",
                  m: { xs: 0.5, sm: 1 },
                  cursor: "pointer",
                  borderRadius: "12px",
                  border: "5px solid #10618E",
                  fontSize: { xs: "25px", sm: "30px", md: "35px", lg: "40px" },
                }}
              >
                <span
                  style={{
                    color: "white",
                    fontWeight: 600,
                    fontSize: {
                      xs: "25px",
                      sm: "35px",
                      md: "40px",
                      lg: "40px",
                    },
                    fontFamily: "Quicksand",
                  }}
                >
                  {elem}
                </span>
              </Box>
            ) : (
              <Box
                onClick={() => handleWords(elem)}
                sx={{
                  textAlign: "center",
                  px: { xs: "15px", sm: "20px", md: "25px" },
                  py: { xs: "8px", sm: "10px", md: "12px" },
                  m: { xs: 0.5, sm: 1 },
                  textTransform: "none",
                  borderRadius: "12px",
                  border: `1px solid rgba(51, 63, 97, 0.10)`,
                  background: "#FFF",
                  cursor: "pointer",
                  fontSize: { xs: "25px", sm: "30px", md: "35px", lg: "40px" },
                }}
              >
                <span
                  style={{
                    color: "#6F80B1",
                    fontWeight: 600,
                    fontFamily: "Quicksand",
                  }}
                >
                  {elem}
                </span>
              </Box>
            )}
          </React.Fragment>
        ))}
      </Box>

      {
        <Box sx={{ display: "flex", justifyContent: "center" }}>
          <VoiceAnalyser
            pageName={"m4"}
            setVoiceText={setVoiceText}
            setVoiceAnimate={setVoiceAnimate}
            dontShowListen={type === "image" || isDiscover}
            // updateStory={updateStory}
            handleNext={handleNext}
            enableNext={enableNext}
            originalText={parentWords}
            audioLink={audio || null}
            {...{
              contentId,
              contentType,
              currentLine: currentStep - 1,
              playTeacherAudio,
              callUpdateLearner,
              isShowCase,
              setEnableNext,
              showOnlyListen: answer !== "correct",
              setOpenMessageDialog,
            }}
          />
        </Box>
      }
    </MainLayout>
  );
};

Mechanics4.propTypes = {
  page: PropTypes.oneOfType([PropTypes.string, PropTypes.number]),
  setPage: PropTypes.func.isRequired,
  handleNext: PropTypes.func.isRequired,
  header: PropTypes.string,
  image: PropTypes.string,
  parentWords: PropTypes.string,
  setVoiceText: PropTypes.func.isRequired,
  setVoiceAnimate: PropTypes.func.isRequired,
  enableNext: PropTypes.bool,
  showTimer: PropTypes.bool,
  points: PropTypes.number,
  currentStep: PropTypes.number.isRequired,
  isDiscover: PropTypes.bool,
  showProgress: PropTypes.bool,
  callUpdateLearner: PropTypes.bool,
  disableScreen: PropTypes.bool,
  isShowCase: PropTypes.bool,
  handleBack: PropTypes.func.isRequired,
  setEnableNext: PropTypes.func.isRequired,
  loading: PropTypes.bool,
  setOpenMessageDialog: PropTypes.func.isRequired,
  playTeacherAudio: PropTypes.func.isRequired,
  background: PropTypes.string,
  type: PropTypes.any,
  steps: PropTypes.number,
  contentId: PropTypes.oneOfType([PropTypes.string, PropTypes.number])
    .isRequired,
  contentType: PropTypes.string,
  level: PropTypes.oneOfType([PropTypes.string, PropTypes.number]).isRequired,
  progressData: PropTypes.object,
  audio: PropTypes.string,
};

export default Mechanics4;
