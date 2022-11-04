package baseball.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SelectedNumberForRestartDTOTest {
    @DisplayName("예외 처리 : null 또는 \"\" 입력 시")
    @ParameterizedTest(name = "{displayName} => {0}")
    @NullAndEmptySource
    void null_or_empty_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SelectedNumberForRestartDTO(input))
                .withMessage("Null 또는 Empty 를 입력할 수 없습니다.");
    }
    
    @DisplayName("예외 처리 : 알파벳 소문자 및 대문자 입력 시")
    @ParameterizedTest(name = "{displayName} => {0}")
    @ValueSource(strings = {"A", "a"})
    void alphabet_exception(String input) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SelectedNumberForRestartDTO(input))
                .withMessage("1 또는 2만 입력할 수 있습니다.");
    }
}