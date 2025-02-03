package hongsunghwi.portfolio.feature.career

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import hongsunghwi.portfolio.core.ui.component.CareerCard
import hongsunghwi.portfolio.core.ui.theme.PortfolioTheme

@Composable
fun CareerContainer(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = "경력",
            modifier = Modifier.fillMaxWidth(),
            style = PortfolioTheme.typography.displayMedium.copy(
                textAlign = TextAlign.Center
            )
        )
        Spacer(Modifier.height(60.dp))
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CareerCard(
                modifier = Modifier.fillMaxWidth(),
                companyName = "(주) 이지테크핀",
                startDate = "2021.12",
                companyIntro = "로보어드바이저 및 블록체인/디지털 월렛 서비스"
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                ) {
                    Column {
                        Text(
                            text = "DayUp(구독형 주식 정보 제공 앱) 개발",
                            style = PortfolioTheme.typography.titleMedium
                        )
                        Text(
                            text = "2023.03 - 2024.12",
                            style = PortfolioTheme.typography.titleSmall
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = buildAnnotatedString {
                                append("- 실시간 지수, 양봉 차트 개발\n")
                                append("- 실시간 주가 정보(호가, 체결, 일별) 화면 개발\n")
                                append("- WebView를 이용한 라이브 스트리밍, VOD 화면 개발\n")
                                append("- Google Play Billing을 이용한 정기결제 개발\n")
                                append("- 멘토 커뮤니티 개발\n")
                            }
                        )
                    }
                    Column {
                        Text(
                            text = "KIWI(주식 자동 매매 앱) 개발 및 유지보수",
                            style = PortfolioTheme.typography.titleMedium
                        )
                        Text(
                            text = "2021.12 - 2023.02",
                            style = PortfolioTheme.typography.titleSmall
                        )
                        Spacer(Modifier.height(8.dp))
                        Text(
                            text = buildAnnotatedString {
                                append("- LS증권 Xing API를 이용하여 매매, 주가 정보 개발\n")
                                append("- 선물 차트 개발\n")
                                append("- 회원가입시 증권사 앱 연동을 통한 계좌 인증 개발\n")
                                append("- 관리종목 백업 및 복구 개발\n")
                                append("- 로그 전송 개발\n")
                            }
                        )
                    }
                }
            }
        }
    }
}