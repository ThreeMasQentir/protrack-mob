package org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.gspi.protrack.common.document.openOrDownloadPdf
import org.gspi.protrack.common.picker.FilePickResult
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectState
import org.gspi.protrack.feature.feat_detail_project.presentation.viewmodel.DetailProjectViewModel
import org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.component.ItemDocumentComponent
import org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.component.NewProjectDocSearchComponent
import org.gspi.protrack.feature.feat_detail_project.other.feat_document.presentation.component.UploadDocumentComponent
import org.gspi.protrack.feature.feat_detail_project.presentation.eventstate.DetailProjectEvent
import org.gspi.protrack.gspidesign.confirmation.ConfirmationDialog
import org.gspi.protrack.gspidesign.empty.EmptyView

@Composable
fun DocumentDetailScreen(
    uiState: DetailProjectState,
    viewModel: DetailProjectViewModel,
    modifier: Modifier = Modifier) {
    LaunchedEffect(Unit){
        viewModel.onEvent(DetailProjectEvent.LoadDocumentList)
    }
    LaunchedEffect(uiState.documentState.metaResponseDocument) {
        uiState.documentState.metaResponseDocument?.let {
            viewModel.onEvent(DetailProjectEvent.LoadDocumentList)
        }
    }
    Box{
        UploadDocumentComponent(
            isDialogVisible = uiState.documentState.isDialogDocumentVisible,
            onDialogVisibleChange = {
                viewModel.onEvent(
                    DetailProjectEvent.OnAddDocumentClick(it)
                )
            },
            projectName = uiState.documentState.documentButtonName,
            onProjectNameChange = {
                when (it) {
                    is FilePickResult.Success -> {
                        viewModel.onEvent(
                            DetailProjectEvent.OnDocumentFileChange(it.data, it.fileName))
                    }
                    else -> {}
                }
            },
            documentName = uiState.documentState.documentName,
            onDocumentNameChange = {
                viewModel.onEvent(
                    DetailProjectEvent.OnDocumentNameChange(it)
                )
            },
            onSaveClick = {
                viewModel.onEvent(
                    DetailProjectEvent.OnSaveDocument
                )
            }
        )
        Column(
            modifier = modifier
                .padding(top = 56.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(16.dp))
            NewProjectDocSearchComponent(
                searchValue = uiState.searchDocumentValue,
                onValueChange = {
                    viewModel.onEvent(
                        DetailProjectEvent.OnSearchDocument(it)
                    )
                },
                onButtonClick = {
                    viewModel.onEvent(
                        DetailProjectEvent.OnAddDocumentClick(true)
                    )
                }
            )

            if (uiState.listDocumentFiltered?.isNotEmpty() == true){
                EmptyView.hide()
                uiState.listDocumentFiltered.forEach {
                    ItemDocumentComponent(
                        projectName = it.documentName,
                        timeLeft = it.dateUploaded,
                        author = it.fileName,
                        onClick = {},
                        onDownloadClick = {
                            openOrDownloadPdf(it.path)
                        },
                        onDeleteClick = {
                            ConfirmationDialog.show(
                                title = "Delete Document",
                                message = "Are you sure you want to delete this document?",
                                onYesClick = {
                                    viewModel.onEvent(
                                        DetailProjectEvent.OnDeleteDocument(it.id)
                                    )
                                }
                            )
                        }
                    )
                }
            }else if (uiState.searchDocumentValue.isNotEmpty()) {
                EmptyView.show("No documents found")
            } else {
                EmptyView.show("No documents available")
            }
        }
    }

}