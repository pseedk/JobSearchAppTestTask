package ru.pvkovalev.jobsearchapptesttask.presentation.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.pvkovalev.jobsearchapptesttask.R
import ru.pvkovalev.jobsearchapptesttask.data.api.model.OfferDto
import ru.pvkovalev.jobsearchapptesttask.data.api.model.VacancyDto
import ru.pvkovalev.jobsearchapptesttask.domain.model.VacancyItem
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.AllVacationsButtonColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.ButtonBackgroundColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.CardBackground
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.CompanyIconTintColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.IconTint
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.LikeTintColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.TempIconBackgroundColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.TempIconTintColor
import ru.pvkovalev.jobsearchapptesttask.presentation.ui.theme.VacancyIconTintColor
import ru.pvkovalev.jobsearchapptesttask.utils.Constants.EMPTY_ID
import ru.pvkovalev.jobsearchapptesttask.utils.Constants.EMPTY_STRING
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.ErrorItem
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.LoadingItem
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.getLookingNumberText
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.openUrl
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.parseToDate
import ru.pvkovalev.jobsearchapptesttask.utils.Functions.parseVacanciesCount
import ru.pvkovalev.jobsearchapptesttask.utils.NetworkResult

@Composable
fun SearchScreen(
    paddingValues: PaddingValues
) {
    val searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
    val context = LocalContext.current
    val searchScreenState = searchScreenViewModel.searchScreenState.collectAsState().value
    val jobSearchDataResponse = searchScreenViewModel.searchWorkData.collectAsState().value
    val jobSearchData = searchScreenViewModel.searchWorkData.collectAsState().value.data
    val recommendations = jobSearchData?.offers ?: listOf()
    val vacancies = jobSearchData?.vacancies ?: listOf()
    val favoriteVacancies = searchScreenViewModel.getVacancies.collectAsState(listOf()).value

    when (jobSearchDataResponse) {
        is NetworkResult.Loading -> {
            LoadingItem()
        }

        is NetworkResult.Error -> {
            ErrorItem(
                errorText = jobSearchDataResponse.message.toString()
            )
        }

        is NetworkResult.Success -> {
            LazyColumn(
                modifier = Modifier
                    .background(color = Color.Black)
                    .fillMaxSize()
                    .padding(16.dp),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                item {
                    when (searchScreenState) {
                        is SearchScreenState.SearchState -> SearchTab()
                        is SearchScreenState.OpenListState -> FilterTab(
                            vacanciesCount = vacancies.size,
                            onBackClick = {
                                searchScreenViewModel.changeSearchScreenState()
                            }
                        )
                    }
                }
                if (searchScreenState is SearchScreenState.SearchState) {
                    if (recommendations.isNotEmpty()) {
                        item {
                            LazyRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                items(
                                    items = recommendations,
                                    key = { it.id ?: EMPTY_ID }
                                ) { recommendation ->
                                    RecommendationsCard(
                                        offerDto = recommendation,
                                        onClickRecommendation = {
                                            openUrl(
                                                context = context,
                                                url = recommendation.link.toString()
                                            )
                                        }
                                    )
                                }
                            }
                        }
                    }
                    item {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = stringResource(R.string.vacations),
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
                if (vacancies.isNotEmpty()) {
                    items(
                        items = when (searchScreenState) {
                            is SearchScreenState.SearchState -> vacancies.take(3)
                            is SearchScreenState.OpenListState -> vacancies
                        },
                        key = { it.id ?: EMPTY_ID }
                    ) { vacancy ->
                        VacancyItem(
                            vacancyDto = vacancy,
                            onClickLike = {
                                val inFavorite = favoriteVacancies.find { it.id == vacancy.id }
                                if (inFavorite == null) {
                                    val vacancyItem = VacancyItem(
                                        id = vacancy.id ?: EMPTY_STRING,
                                        lookingNumber = vacancy.lookingNumber ?: EMPTY_ID,
                                        title = vacancy.title ?: EMPTY_STRING,
                                        town = vacancy.addressDto?.town ?: EMPTY_STRING,
                                        company = vacancy.company ?: EMPTY_STRING,
                                        experience = vacancy.experienceDto?.previewText
                                            ?: EMPTY_STRING,
                                        publishedDate = vacancy.publishedDate ?: EMPTY_STRING,
                                        isFavorite = vacancy.isFavorite ?: false
                                    )
                                    searchScreenViewModel.addVacancy(vacancyItem)
                                } else {
                                    searchScreenViewModel.deleteVacancy(inFavorite)
                                }
                            }
                        )
                    }
                }
                if (searchScreenState is SearchScreenState.SearchState) {
                    item {
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = AllVacationsButtonColor),
                            onClick = {
                                searchScreenViewModel.changeSearchScreenState()
                            }
                        ) {
                            Text(
                                text = "${stringResource(R.string.still)} ${vacancies.size.parseVacanciesCount()}",
                                color = Color.White,
                                fontSize = 16.sp,
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun SearchTab() {
    Row {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(40.dp)
                .fillMaxWidth()
                .weight(1f)
                .background(color = CardBackground)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.ic_search_work),
                tint = IconTint,
                contentDescription = stringResource(R.string.search_icon)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.keywords),
                color = IconTint,
                fontSize = 14.sp
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .size(40.dp)
                .background(color = CardBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                modifier = Modifier
                    .size(24.dp),
                tint = IconTint,
                painter = painterResource(id = R.drawable.ic_filter),
                contentDescription = stringResource(R.string.filter)
            )
        }
    }
}

@Composable
fun FilterTab(
    onBackClick: () -> Unit,
    vacanciesCount: Int
) {
    Column {
        Row {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(40.dp)
                    .fillMaxWidth()
                    .weight(1f)
                    .background(color = CardBackground)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .clickable {
                            onBackClick()
                        }
                        .size(24.dp),
                    imageVector = Icons.Default.ArrowBack,
                    tint = IconTint,
                    contentDescription = stringResource(R.string.search_icon)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = stringResource(R.string.position_for_suitable_vacancies),
                    color = IconTint,
                    fontSize = 14.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .size(40.dp)
                    .background(color = CardBackground),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    tint = IconTint,
                    painter = painterResource(id = R.drawable.ic_filter),
                    contentDescription = stringResource(R.string.filter)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = vacanciesCount.parseVacanciesCount(),
                color = Color.White,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.by_correspondence),
                    color = AllVacationsButtonColor,
                    fontSize = 14.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    modifier = Modifier
                        .size(24.dp),
                    tint = AllVacationsButtonColor,
                    painter = painterResource(id = R.drawable.ic_arrows),
                    contentDescription = stringResource(R.string.correspondence)
                )
            }
        }
    }
}

@Composable
fun RecommendationsCard(
    offerDto: OfferDto,
    onClickRecommendation: () -> Unit
) {
    val link = offerDto.link ?: EMPTY_STRING
    Card(
        modifier = Modifier
            .clickable {
                if (link.isNotBlank()) {
                    onClickRecommendation()
                }
            }
            .height(150.dp)
            .width(150.dp),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = CardBackground)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            val id = offerDto.id ?: EMPTY_STRING
            if (id.isNotBlank()) {
                val iconResId = when (id) {
                    "near_vacancies" -> R.drawable.ic_location
                    "level_up_resume" -> R.drawable.ic_favorite
                    "temporary_job" -> R.drawable.ic_temp
                    else -> R.drawable.ic_location
                }
                val iconBackground = when (id) {
                    "near_vacancies" -> VacancyIconTintColor
                    "level_up_resume", "temporary_job" -> TempIconBackgroundColor
                    else -> TempIconTintColor
                }
                val iconTint = when (id) {
                    "near_vacancies" -> LikeTintColor
                    "level_up_resume", "temporary_job" -> TempIconTintColor
                    else -> TempIconTintColor
                }
                IconButton(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(iconBackground),
                    onClick = {}
                ) {
                    Icon(
                        modifier = Modifier
                            .size(24.dp),
                        tint = iconTint,
                        painter = painterResource(id = iconResId),
                        contentDescription = stringResource(R.string.recommendations_icon)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
            val title = offerDto.title ?: EMPTY_STRING
            val button = offerDto.buttonDto
            if (title.isNotBlank()) {
                Text(
                    text = title.trim(),
                    color = Color.White,
                    fontSize = 14.sp,
                    maxLines = if (button == null) 3 else 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            if (button != null) {
                val buttonText = button.text ?: EMPTY_STRING
                Text(
                    text = buttonText,
                    color = ButtonBackgroundColor,
                    fontSize = 14.sp,
                )
            }
        }
    }
}

@Composable
fun VacancyItem(
    vacancyDto: VacancyDto,
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
                    val lookingNumber = vacancyDto.lookingNumber ?: 0
                    if (lookingNumber != 0) {
                        Text(
                            text = lookingNumber.getLookingNumberText(),
                            color = TempIconTintColor,
                            fontSize = 14.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    val title = vacancyDto.title ?: EMPTY_STRING
                    if (title.isNotBlank()) {
                        Text(
                            text = title,
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                    val town = vacancyDto.addressDto?.town ?: EMPTY_STRING
                    if (town.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = town,
                            color = Color.White,
                            fontSize = 14.sp,
                        )
                    }
                    val company = vacancyDto.company ?: EMPTY_STRING
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
                    val experience = vacancyDto.experienceDto?.previewText ?: EMPTY_STRING
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
                    val date = vacancyDto.publishedDate ?: EMPTY_STRING
                    if (date.isNotBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "${stringResource(R.string.published)} ${date.parseToDate()}",
                            color = CompanyIconTintColor,
                            fontSize = 14.sp,
                        )
                    }
                }
                val isFavorite = vacancyDto.isFavorite ?: false
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

