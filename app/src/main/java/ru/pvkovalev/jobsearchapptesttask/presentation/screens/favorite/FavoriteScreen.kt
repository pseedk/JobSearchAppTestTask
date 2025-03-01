package ru.pvkovalev.jobsearchapptesttask.presentation.screens.favorite

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.pvkovalev.jobsearchapptesttask.R
import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.ButtonBackgroundColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.CardBackground
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.CompanyIconTintColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.LikeTintColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.TempIconTintColor
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.getLookingNumberText
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.parseToDate
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.parseVacanciesCount

@Composable
fun FavoriteScreen(
    paddingValues: PaddingValues
) {
    val favoriteScreenViewModel: FavoriteScreenViewModel = hiltViewModel()
    val vacancies = favoriteScreenViewModel.getVacancies.collectAsState(listOf()).value

    LazyColumn(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = paddingValues,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                text = stringResource(R.string.favour),
                fontSize = 20.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (vacancies.isNotEmpty()) {
            item {
                Text(
                    text = vacancies.size.parseVacanciesCount(),
                    fontSize = 14.sp,
                    color = CompanyIconTintColor
                )
            }
            items(
                items = vacancies,
                key = { it.id }
            ) { vacancy ->
                VacancyFavoriteItem(
                    vacancyItem = vacancy,
                    onClickLike = {
                        favoriteScreenViewModel.deleteVacancy(vacancy)
                    }
                )
            }
        }
    }
}

@Composable
fun VacancyFavoriteItem(
    vacancyItem: VacancyItem,
    onClickLike: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    val lookingNumber = vacancyItem.lookingNumber
                    if (lookingNumber != 0) {
                        Text(
                            text = lookingNumber.getLookingNumberText(),
                            color = TempIconTintColor,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    val title = vacancyItem.title
                    if (title.isNotBlank()) {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    val town = vacancyItem.town
                    if (town.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = town,
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }
                    val company = vacancyItem.company
                    if (company.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = company,
                                color = Color.White,
                                fontSize = 14.sp,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                tint = CompanyIconTintColor,
                                painter = painterResource(id = R.drawable.ic_company),
                                contentDescription = stringResource(R.string.company_icon)
                            )
                        }
                    }
                    val experience = vacancyItem.experience
                    if (experience.isNotBlank()) {
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                modifier = Modifier
                                    .size(16.dp),
                                tint = Color.White,
                                painter = painterResource(id = R.drawable.ic_experience),
                                contentDescription = stringResource(R.string.experience)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = experience,
                                color = Color.White,
                                fontSize = 14.sp,
                            )
                        }
                    }
                    val date = vacancyItem.publishedDate
                    if (date.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(R.string.published)} ${date.parseToDate()}",
                            color = CompanyIconTintColor,
                            fontSize = 14.sp,
                        )
                    }
                }
                val isFavorite = vacancyItem.isFavorite
                Spacer(modifier = Modifier.width(8.dp))
                val iconResId = if (isFavorite) R.drawable.ic_like else R.drawable.ic_unlike
                Icon(
                    modifier = Modifier
                        .clickable {
                            onClickLike()
                        }
                        .size(24.dp),
                    tint = LikeTintColor,
                    painter = painterResource(id = iconResId),
                    contentDescription = stringResource(R.string.in_favorite)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                shape = CircleShape,
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = ButtonBackgroundColor),
                onClick = {}
            ) {
                Text(
                    text = stringResource(R.string.respond),
                    color = Color.White,
                    fontSize = 14.sp,
                )
            }
        }
    }
}
