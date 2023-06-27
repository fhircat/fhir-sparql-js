package org.example.fhir.cat;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;

public class FhirRdf {
	/**
	 * The namespace of the vocabulary as a string
	 */
	public static final String NS = "http://hl7.org/fhir/";

	/**
	 * returns the URI for this schema
	 * 
	 * @return the URI for this schema
	 */
	public static String getURI() {
		return NS;
	}

	public static final Resource Accident = ResourceFactory.createResource(NS + "Accident");
	public static final Resource Account = ResourceFactory.createResource(NS + "Account");
	public static final Resource AccountBalanceComponent = ResourceFactory
			.createResource(NS + "AccountBalanceComponent");
	public static final Resource AccountDiagnosisComponent = ResourceFactory
			.createResource(NS + "AccountDiagnosisComponent");
	public static final Resource AccountProcedureComponent = ResourceFactory
			.createResource(NS + "AccountProcedureComponent");
	public static final Resource AccountRelatedAccountComponent = ResourceFactory
			.createResource(NS + "AccountRelatedAccountComponent");
	public static final Resource Action = ResourceFactory.createResource(NS + "Action");
	public static final Resource ActionSubject = ResourceFactory.createResource(NS + "ActionSubject");
	public static final Resource ActivityDefinition = ResourceFactory.createResource(NS + "ActivityDefinition");
	public static final Resource ActivityDefinitionDynamicValueComponent = ResourceFactory
			.createResource(NS + "ActivityDefinitionDynamicValueComponent");
	public static final Resource ActivityDefinitionParticipantComponent = ResourceFactory
			.createResource(NS + "ActivityDefinitionParticipantComponent");
	public static final Resource ActorDefinition = ResourceFactory.createResource(NS + "ActorDefinition");
	public static final Resource AddedItem = ResourceFactory.createResource(NS + "AddedItem");
	public static final Resource AddedItemBodySite = ResourceFactory.createResource(NS + "AddedItemBodySite");
	public static final Resource AddedItemDetail = ResourceFactory.createResource(NS + "AddedItemDetail");
	public static final Resource AddedItemDetailSubDetail = ResourceFactory
			.createResource(NS + "AddedItemDetailSubDetail");
	public static final Resource AddedItemSubDetail = ResourceFactory.createResource(NS + "AddedItemSubDetail");
	public static final Resource AdditionalAttribute = ResourceFactory.createResource(NS + "AdditionalAttribute");
	public static final Resource Address = ResourceFactory.createResource(NS + "Address");
	public static final Resource Adjudication = ResourceFactory.createResource(NS + "Adjudication");
	public static final Resource AdministrableProductDefinition = ResourceFactory
			.createResource(NS + "AdministrableProductDefinition");
	public static final Resource AdministrableProductDefinitionPropertyComponent = ResourceFactory
			.createResource(NS + "AdministrableProductDefinitionPropertyComponent");
	public static final Resource AdministrableProductDefinitionRouteOfAdministrationComponent = ResourceFactory
			.createResource(NS + "AdministrableProductDefinitionRouteOfAdministrationComponent");
	public static final Resource AdministrableProductDefinitionRouteOfAdministrationTargetSpeciesComponent = ResourceFactory
			.createResource(NS + "AdministrableProductDefinitionRouteOfAdministrationTargetSpeciesComponent");
	public static final Resource AdministrableProductDefinitionRouteOfAdministrationTargetSpeciesWithdrawalPeriodComponent = ResourceFactory
			.createResource(
					NS + "AdministrableProductDefinitionRouteOfAdministrationTargetSpeciesWithdrawalPeriodComponent");
	public static final Resource AdverseEvent = ResourceFactory.createResource(NS + "AdverseEvent");
	public static final Resource AdverseEventContributingFactorComponent = ResourceFactory
			.createResource(NS + "AdverseEventContributingFactorComponent");
	public static final Resource AdverseEventMitigatingActionComponent = ResourceFactory
			.createResource(NS + "AdverseEventMitigatingActionComponent");
	public static final Resource AdverseEventParticipantComponent = ResourceFactory
			.createResource(NS + "AdverseEventParticipantComponent");
	public static final Resource AdverseEventPreventiveActionComponent = ResourceFactory
			.createResource(NS + "AdverseEventPreventiveActionComponent");
	public static final Resource AdverseEventSupportingInfoComponent = ResourceFactory
			.createResource(NS + "AdverseEventSupportingInfoComponent");
	public static final Resource AdverseEventSuspectEntityCausalityComponent = ResourceFactory
			.createResource(NS + "AdverseEventSuspectEntityCausalityComponent");
	public static final Resource AdverseEventSuspectEntityComponent = ResourceFactory
			.createResource(NS + "AdverseEventSuspectEntityComponent");
	public static final Resource Age = ResourceFactory.createResource(NS + "Age");
	public static final Resource AllergyIntolerance = ResourceFactory.createResource(NS + "AllergyIntolerance");
	public static final Resource AllergyIntoleranceParticipantComponent = ResourceFactory
			.createResource(NS + "AllergyIntoleranceParticipantComponent");
	public static final Resource AllergyIntoleranceReactionComponent = ResourceFactory
			.createResource(NS + "AllergyIntoleranceReactionComponent");
	public static final Resource Annotation = ResourceFactory.createResource(NS + "Annotation");
	public static final Resource Answer = ResourceFactory.createResource(NS + "Answer");
	public static final Resource Appointment = ResourceFactory.createResource(NS + "Appointment");
	public static final Resource AppointmentParticipantComponent = ResourceFactory
			.createResource(NS + "AppointmentParticipantComponent");
	public static final Resource AppointmentRecurrenceTemplateComponent = ResourceFactory
			.createResource(NS + "AppointmentRecurrenceTemplateComponent");
	public static final Resource AppointmentRecurrenceTemplateMonthlyTemplateComponent = ResourceFactory
			.createResource(NS + "AppointmentRecurrenceTemplateMonthlyTemplateComponent");
	public static final Resource AppointmentRecurrenceTemplateWeeklyTemplateComponent = ResourceFactory
			.createResource(NS + "AppointmentRecurrenceTemplateWeeklyTemplateComponent");
	public static final Resource AppointmentRecurrenceTemplateYearlyTemplateComponent = ResourceFactory
			.createResource(NS + "AppointmentRecurrenceTemplateYearlyTemplateComponent");
	public static final Resource AppointmentResponse = ResourceFactory.createResource(NS + "AppointmentResponse");
	public static final Resource ArtifactAssessment = ResourceFactory.createResource(NS + "ArtifactAssessment");
	public static final Resource ArtifactAssessmentContentComponent = ResourceFactory
			.createResource(NS + "ArtifactAssessmentContentComponent");
	public static final Resource ArtifactAssessmentContentComponentContent = ResourceFactory
			.createResource(NS + "ArtifactAssessmentContentComponentContent");
	public static final Resource ArtifactAssessmentContentComponentContentContent = ResourceFactory
			.createResource(NS + "ArtifactAssessmentContentComponentContentContent");
	public static final Resource AssetContext = ResourceFactory.createResource(NS + "AssetContext");
	public static final Resource Attachment = ResourceFactory.createResource(NS + "Attachment");
	public static final Resource AuditEvent = ResourceFactory.createResource(NS + "AuditEvent");
	public static final Resource AuditEventAgentComponent = ResourceFactory
			.createResource(NS + "AuditEventAgentComponent");
	public static final Resource AuditEventEntityComponent = ResourceFactory
			.createResource(NS + "AuditEventEntityComponent");
	public static final Resource AuditEventEntityComponentAgent = ResourceFactory
			.createResource(NS + "AuditEventEntityComponentAgent");
	public static final Resource AuditEventEntityDetailComponent = ResourceFactory
			.createResource(NS + "AuditEventEntityDetailComponent");
	public static final Resource AuditEventOutcomeComponent = ResourceFactory
			.createResource(NS + "AuditEventOutcomeComponent");
	public static final Resource AuditEventSourceComponent = ResourceFactory
			.createResource(NS + "AuditEventSourceComponent");
	public static final Resource Availability = ResourceFactory.createResource(NS + "Availability");
	public static final Resource AvailabilityAvailableTimeComponent = ResourceFactory
			.createResource(NS + "AvailabilityAvailableTimeComponent");
	public static final Resource AvailabilityNotAvailableTimeComponent = ResourceFactory
			.createResource(NS + "AvailabilityNotAvailableTimeComponent");
	public static final Resource BackboneElement = ResourceFactory.createResource(NS + "BackboneElement");
	public static final Resource BackboneType = ResourceFactory.createResource(NS + "BackboneType");
	public static final Resource Base = ResourceFactory.createResource(NS + "Base");
	public static final Resource Basic = ResourceFactory.createResource(NS + "Basic");
	public static final Resource Benefit = ResourceFactory.createResource(NS + "Benefit");
	public static final Resource BenefitBalance = ResourceFactory.createResource(NS + "BenefitBalance");
	public static final Resource Binary = ResourceFactory.createResource(NS + "Binary");
	public static final Resource BiologicallyDerivedProduct = ResourceFactory
			.createResource(NS + "BiologicallyDerivedProduct");
	public static final Resource BiologicallyDerivedProductCollectionComponent = ResourceFactory
			.createResource(NS + "BiologicallyDerivedProductCollectionComponent");
	public static final Resource BiologicallyDerivedProductDispense = ResourceFactory
			.createResource(NS + "BiologicallyDerivedProductDispense");
	public static final Resource BiologicallyDerivedProductDispensePerformerComponent = ResourceFactory
			.createResource(NS + "BiologicallyDerivedProductDispensePerformerComponent");
	public static final Resource BiologicallyDerivedProductPropertyComponent = ResourceFactory
			.createResource(NS + "BiologicallyDerivedProductPropertyComponent");
	public static final Resource BodySite = ResourceFactory.createResource(NS + "BodySite");
	public static final Resource BodyStructure = ResourceFactory.createResource(NS + "BodyStructure");
	public static final Resource BodyStructureIncludedStructure = ResourceFactory
			.createResource(NS + "BodyStructureIncludedStructure");
	public static final Resource BodyStructureIncludedStructureBodyLandmarkOrientationComponent = ResourceFactory
			.createResource(NS + "BodyStructureIncludedStructureBodyLandmarkOrientationComponent");
	public static final Resource BodyStructureIncludedStructureBodyLandmarkOrientationDistanceFromLandmarkComponent = ResourceFactory
			.createResource(NS + "BodyStructureIncludedStructureBodyLandmarkOrientationDistanceFromLandmarkComponent");
	public static final Resource BodyStructureIncludedStructureComponent = ResourceFactory
			.createResource(NS + "BodyStructureIncludedStructureComponent");
	public static final Resource Bundle = ResourceFactory.createResource(NS + "Bundle");
	public static final Resource BundleEntryComponent = ResourceFactory.createResource(NS + "BundleEntryComponent");
	public static final Resource BundleEntryComponentLink = ResourceFactory
			.createResource(NS + "BundleEntryComponentLink");
	public static final Resource BundleEntryRequestComponent = ResourceFactory
			.createResource(NS + "BundleEntryRequestComponent");
	public static final Resource BundleEntryResponseComponent = ResourceFactory
			.createResource(NS + "BundleEntryResponseComponent");
	public static final Resource BundleEntrySearchComponent = ResourceFactory
			.createResource(NS + "BundleEntrySearchComponent");
	public static final Resource BundleLinkComponent = ResourceFactory.createResource(NS + "BundleLinkComponent");
	public static final Resource CanonicalResource = ResourceFactory.createResource(NS + "CanonicalResource");
	public static final Resource CapabilityStatement = ResourceFactory.createResource(NS + "CapabilityStatement");
	public static final Resource CapabilityStatementDocumentComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementDocumentComponent");
	public static final Resource CapabilityStatementImplementationComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementImplementationComponent");
	public static final Resource CapabilityStatementMessagingComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementMessagingComponent");
	public static final Resource CapabilityStatementMessagingEndpointComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementMessagingEndpointComponent");
	public static final Resource CapabilityStatementMessagingSupportedMessageComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementMessagingSupportedMessageComponent");
	public static final Resource CapabilityStatementRestComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementRestComponent");
	public static final Resource CapabilityStatementRestComponentOperation = ResourceFactory
			.createResource(NS + "CapabilityStatementRestComponentOperation");
	public static final Resource CapabilityStatementRestComponentSearchParam = ResourceFactory
			.createResource(NS + "CapabilityStatementRestComponentSearchParam");
	public static final Resource CapabilityStatementRestResourceComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementRestResourceComponent");
	public static final Resource CapabilityStatementRestResourceOperationComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementRestResourceOperationComponent");
	public static final Resource CapabilityStatementRestResourceSearchParamComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementRestResourceSearchParamComponent");
	public static final Resource CapabilityStatementRestSecurityComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementRestSecurityComponent");
	public static final Resource CapabilityStatementSoftwareComponent = ResourceFactory
			.createResource(NS + "CapabilityStatementSoftwareComponent");
	public static final Resource CarePlan = ResourceFactory.createResource(NS + "CarePlan");
	public static final Resource CarePlanActivityComponent = ResourceFactory
			.createResource(NS + "CarePlanActivityComponent");
	public static final Resource CareTeam = ResourceFactory.createResource(NS + "CareTeam");
	public static final Resource CareTeamParticipantComponent = ResourceFactory
			.createResource(NS + "CareTeamParticipantComponent");
	public static final Resource ChargeItem = ResourceFactory.createResource(NS + "ChargeItem");
	public static final Resource ChargeItemDefinition = ResourceFactory.createResource(NS + "ChargeItemDefinition");
	public static final Resource ChargeItemDefinitionApplicabilityComponent = ResourceFactory
			.createResource(NS + "ChargeItemDefinitionApplicabilityComponent");
	public static final Resource ChargeItemDefinitionPropertyGroupComponent = ResourceFactory
			.createResource(NS + "ChargeItemDefinitionPropertyGroupComponent");
	public static final Resource ChargeItemDefinitionPropertyGroupComponentApplicability = ResourceFactory
			.createResource(NS + "ChargeItemDefinitionPropertyGroupComponentApplicability");
	public static final Resource ChargeItemPerformerComponent = ResourceFactory
			.createResource(NS + "ChargeItemPerformerComponent");
	public static final Resource Citation = ResourceFactory.createResource(NS + "Citation");
	public static final Resource CitationCitedArtifactAbstractComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactAbstractComponent");
	public static final Resource CitationCitedArtifactClassificationComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactClassificationComponent");
	public static final Resource CitationCitedArtifactComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactComponent");
	public static final Resource CitationCitedArtifactContributorshipComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactContributorshipComponent");
	public static final Resource CitationCitedArtifactContributorshipEntryComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactContributorshipEntryComponent");
	public static final Resource CitationCitedArtifactContributorshipEntryContributionInstanceComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactContributorshipEntryContributionInstanceComponent");
	public static final Resource CitationCitedArtifactPartComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactPartComponent");
	public static final Resource CitationCitedArtifactPublicationFormComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactPublicationFormComponent");
	public static final Resource CitationCitedArtifactPublicationFormPublishedInComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactPublicationFormPublishedInComponent");
	public static final Resource CitationCitedArtifactRelatesToComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactRelatesToComponent");
	public static final Resource CitationCitedArtifactStatusDateComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactStatusDateComponent");
	public static final Resource CitationCitedArtifactTitleComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactTitleComponent");
	public static final Resource CitationCitedArtifactVersionComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactVersionComponent");
	public static final Resource CitationCitedArtifactWebLocationComponent = ResourceFactory
			.createResource(NS + "CitationCitedArtifactWebLocationComponent");
	public static final Resource CitationClassificationComponent = ResourceFactory
			.createResource(NS + "CitationClassificationComponent");
	public static final Resource CitationStatusDateComponent = ResourceFactory
			.createResource(NS + "CitationStatusDateComponent");
	public static final Resource CitationSummaryComponent = ResourceFactory
			.createResource(NS + "CitationSummaryComponent");
	public static final Resource Claim = ResourceFactory.createResource(NS + "Claim");
	public static final Resource ClaimEventComponent = ResourceFactory.createResource(NS + "ClaimEventComponent");
	public static final Resource ClaimResponse = ResourceFactory.createResource(NS + "ClaimResponse");
	public static final Resource ClaimResponse_AddedItem_AddedItemDetail_AddedItemSubDetailAdjudication = ResourceFactory
			.createResource(NS + "ClaimResponse.AddedItem.AddedItemDetail.AddedItemSubDetailAdjudication");
	public static final Resource ClaimResponse_AddedItem_AddedItemDetail_AddedItemSubDetailReviewOutcome = ResourceFactory
			.createResource(NS + "ClaimResponse.AddedItem.AddedItemDetail.AddedItemSubDetailReviewOutcome");
	public static final Resource ClaimResponse_AddedItem_AddedItemDetailAdjudication = ResourceFactory
			.createResource(NS + "ClaimResponse.AddedItem.AddedItemDetailAdjudication");
	public static final Resource ClaimResponse_AddedItem_AddedItemDetailReviewOutcome = ResourceFactory
			.createResource(NS + "ClaimResponse.AddedItem.AddedItemDetailReviewOutcome");
	public static final Resource ClaimResponse_AddedItemAdjudication = ResourceFactory
			.createResource(NS + "ClaimResponse.AddedItemAdjudication");
	public static final Resource ClaimResponse_AddedItemReviewOutcome = ResourceFactory
			.createResource(NS + "ClaimResponse.AddedItemReviewOutcome");
	public static final Resource ClaimResponse_Item_ItemDetail_SubDetailAdjudication = ResourceFactory
			.createResource(NS + "ClaimResponse.Item.ItemDetail.SubDetailAdjudication");
	public static final Resource ClaimResponse_Item_ItemDetail_SubDetailReviewOutcome = ResourceFactory
			.createResource(NS + "ClaimResponse.Item.ItemDetail.SubDetailReviewOutcome");
	public static final Resource ClaimResponse_Item_ItemDetailAdjudication = ResourceFactory
			.createResource(NS + "ClaimResponse.Item.ItemDetailAdjudication");
	public static final Resource ClaimResponse_Item_ItemDetailReviewOutcome = ResourceFactory
			.createResource(NS + "ClaimResponse.Item.ItemDetailReviewOutcome");
	public static final Resource ClaimResponseAdjudication = ResourceFactory
			.createResource(NS + "ClaimResponseAdjudication");
	public static final Resource ClaimResponseEventComponent = ResourceFactory
			.createResource(NS + "ClaimResponseEventComponent");
	public static final Resource Class = ResourceFactory.createResource(NS + "Class");
	public static final Resource ClinicalImpression = ResourceFactory.createResource(NS + "ClinicalImpression");
	public static final Resource ClinicalImpressionFindingComponent = ResourceFactory
			.createResource(NS + "ClinicalImpressionFindingComponent");
	public static final Resource ClinicalUseDefinition = ResourceFactory.createResource(NS + "ClinicalUseDefinition");
	public static final Resource ClinicalUseDefinitionContraindicationComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionContraindicationComponent");
	public static final Resource ClinicalUseDefinitionContraindicationOtherTherapyComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionContraindicationOtherTherapyComponent");
	public static final Resource ClinicalUseDefinitionIndicationComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionIndicationComponent");
	public static final Resource ClinicalUseDefinitionIndicationComponentOtherTherapy = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionIndicationComponentOtherTherapy");
	public static final Resource ClinicalUseDefinitionInteractionComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionInteractionComponent");
	public static final Resource ClinicalUseDefinitionInteractionInteractantComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionInteractionInteractantComponent");
	public static final Resource ClinicalUseDefinitionUndesirableEffectComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionUndesirableEffectComponent");
	public static final Resource ClinicalUseDefinitionWarningComponent = ResourceFactory
			.createResource(NS + "ClinicalUseDefinitionWarningComponent");
	public static final Resource CodeSystem = ResourceFactory.createResource(NS + "CodeSystem");
	public static final Resource CodeSystem_ConceptDefinitionConcept = ResourceFactory
			.createResource(NS + "CodeSystem.ConceptDefinitionConcept");
	public static final Resource CodeSystem_ConceptDefinitionConceptConcept = ResourceFactory
			.createResource(NS + "CodeSystem.ConceptDefinitionConceptConcept");
	public static final Resource CodeSystemConceptDesignationComponent = ResourceFactory
			.createResource(NS + "CodeSystemConceptDesignationComponent");
	public static final Resource CodeSystemFilterComponent = ResourceFactory
			.createResource(NS + "CodeSystemFilterComponent");
	public static final Resource CodeableConcept = ResourceFactory.createResource(NS + "CodeableConcept");
	public static final Resource CodeableReference = ResourceFactory.createResource(NS + "CodeableReference");
	public static final Resource Coding = ResourceFactory.createResource(NS + "Coding");
	public static final Resource Communication = ResourceFactory.createResource(NS + "Communication");
	public static final Resource CommunicationPayloadComponent = ResourceFactory
			.createResource(NS + "CommunicationPayloadComponent");
	public static final Resource CommunicationRequest = ResourceFactory.createResource(NS + "CommunicationRequest");
	public static final Resource CommunicationRequestPayloadComponent = ResourceFactory
			.createResource(NS + "CommunicationRequestPayloadComponent");
	public static final Resource CompartmentDefinition = ResourceFactory.createResource(NS + "CompartmentDefinition");
	public static final Resource CompartmentDefinitionResourceComponent = ResourceFactory
			.createResource(NS + "CompartmentDefinitionResourceComponent");
	public static final Resource Composition = ResourceFactory.createResource(NS + "Composition");
	public static final Resource Composition_SectionSection = ResourceFactory
			.createResource(NS + "Composition.SectionSection");
	public static final Resource Composition_SectionSectionSection = ResourceFactory
			.createResource(NS + "Composition.SectionSectionSection");
	public static final Resource CompositionAttesterComponent = ResourceFactory
			.createResource(NS + "CompositionAttesterComponent");
	public static final Resource CompositionEventComponent = ResourceFactory
			.createResource(NS + "CompositionEventComponent");
	public static final Resource ComputableLanguage = ResourceFactory.createResource(NS + "ComputableLanguage");
	public static final Resource ConceptDefinition = ResourceFactory.createResource(NS + "ConceptDefinition");
	public static final Resource ConceptMap = ResourceFactory.createResource(NS + "ConceptMap");
	public static final Resource ConceptMapGroupComponent = ResourceFactory
			.createResource(NS + "ConceptMapGroupComponent");
	public static final Resource ConceptMapGroupComponent_SourceElement_TargetElementDependsOn = ResourceFactory
			.createResource(NS + "ConceptMapGroupComponent.SourceElement.TargetElementDependsOn");
	public static final Resource ConceptMapGroupUnmappedComponent = ResourceFactory
			.createResource(NS + "ConceptMapGroupUnmappedComponent");
	public static final Resource ConceptProperty = ResourceFactory.createResource(NS + "ConceptProperty");
	public static final Resource ConceptReference = ResourceFactory.createResource(NS + "ConceptReference");
	public static final Resource ConceptSet = ResourceFactory.createResource(NS + "ConceptSet");
	public static final Resource ConceptSubProperty = ResourceFactory.createResource(NS + "ConceptSubProperty");
	public static final Resource Condition = ResourceFactory.createResource(NS + "Condition");
	public static final Resource ConditionDefinition = ResourceFactory.createResource(NS + "ConditionDefinition");
	public static final Resource ConditionDefinitionMedicationComponent = ResourceFactory
			.createResource(NS + "ConditionDefinitionMedicationComponent");
	public static final Resource ConditionDefinitionObservationComponent = ResourceFactory
			.createResource(NS + "ConditionDefinitionObservationComponent");
	public static final Resource ConditionDefinitionPlanComponent = ResourceFactory
			.createResource(NS + "ConditionDefinitionPlanComponent");
	public static final Resource ConditionDefinitionPreconditionComponent = ResourceFactory
			.createResource(NS + "ConditionDefinitionPreconditionComponent");
	public static final Resource ConditionDefinitionQuestionnaireComponent = ResourceFactory
			.createResource(NS + "ConditionDefinitionQuestionnaireComponent");
	public static final Resource ConditionParticipantComponent = ResourceFactory
			.createResource(NS + "ConditionParticipantComponent");
	public static final Resource ConditionStageComponent = ResourceFactory
			.createResource(NS + "ConditionStageComponent");
	public static final Resource Consent = ResourceFactory.createResource(NS + "Consent");
	public static final Resource Consent_provisionProvision = ResourceFactory
			.createResource(NS + "Consent.provisionProvision");
	public static final Resource Consent_provisionProvisionProvision = ResourceFactory
			.createResource(NS + "Consent.provisionProvisionProvision");
	public static final Resource ConsentPolicyBasisComponent = ResourceFactory
			.createResource(NS + "ConsentPolicyBasisComponent");
	public static final Resource ConsentVerificationComponent = ResourceFactory
			.createResource(NS + "ConsentVerificationComponent");
	public static final Resource Contact = ResourceFactory.createResource(NS + "Contact");
	public static final Resource ContactDetail = ResourceFactory.createResource(NS + "ContactDetail");
	public static final Resource ContactPoint = ResourceFactory.createResource(NS + "ContactPoint");
	public static final Resource ContentDefinition = ResourceFactory.createResource(NS + "ContentDefinition");
	public static final Resource Contract = ResourceFactory.createResource(NS + "Contract");
	public static final Resource Contract_Term_ContractAssetAnswer = ResourceFactory
			.createResource(NS + "Contract.Term.ContractAssetAnswer");
	public static final Resource Contract_TermTerm = ResourceFactory.createResource(NS + "Contract.TermTerm");
	public static final Resource Contract_TermTerm_ContractAssetAnswer = ResourceFactory
			.createResource(NS + "Contract.TermTerm.ContractAssetAnswer");
	public static final Resource Contract_TermTermTerm = ResourceFactory.createResource(NS + "Contract.TermTermTerm");
	public static final Resource ContractAsset = ResourceFactory.createResource(NS + "ContractAsset");
	public static final Resource ContractOffer = ResourceFactory.createResource(NS + "ContractOffer");
	public static final Resource ContractParty = ResourceFactory.createResource(NS + "ContractParty");
	public static final Resource Contributor = ResourceFactory.createResource(NS + "Contributor");
	public static final Resource ContributorshipSummary = ResourceFactory.createResource(NS + "ContributorshipSummary");
	public static final Resource CostToBeneficiary = ResourceFactory.createResource(NS + "CostToBeneficiary");
	public static final Resource Count = ResourceFactory.createResource(NS + "Count");
	public static final Resource Coverage = ResourceFactory.createResource(NS + "Coverage");
	public static final Resource CoverageBenefit = ResourceFactory.createResource(NS + "CoverageBenefit");
	public static final Resource CoverageEligibilityRequest = ResourceFactory
			.createResource(NS + "CoverageEligibilityRequest");
	public static final Resource CoverageEligibilityRequestEventComponent = ResourceFactory
			.createResource(NS + "CoverageEligibilityRequestEventComponent");
	public static final Resource CoverageEligibilityResponse = ResourceFactory
			.createResource(NS + "CoverageEligibilityResponse");
	public static final Resource CoverageEligibilityResponseEventComponent = ResourceFactory
			.createResource(NS + "CoverageEligibilityResponseEventComponent");
	public static final Resource CoveragePaymentByComponent = ResourceFactory
			.createResource(NS + "CoveragePaymentByComponent");
	public static final Resource DataRequirement = ResourceFactory.createResource(NS + "DataRequirement");
	public static final Resource DataRequirementCodeFilterComponent = ResourceFactory
			.createResource(NS + "DataRequirementCodeFilterComponent");
	public static final Resource DataRequirementDateFilterComponent = ResourceFactory
			.createResource(NS + "DataRequirementDateFilterComponent");
	public static final Resource DataRequirementSortComponent = ResourceFactory
			.createResource(NS + "DataRequirementSortComponent");
	public static final Resource DataRequirementValueFilterComponent = ResourceFactory
			.createResource(NS + "DataRequirementValueFilterComponent");
	public static final Resource DataType = ResourceFactory.createResource(NS + "DataType");
	public static final Resource Detail = ResourceFactory.createResource(NS + "Detail");
	public static final Resource Details = ResourceFactory.createResource(NS + "Details");
	public static final Resource DetectedIssue = ResourceFactory.createResource(NS + "DetectedIssue");
	public static final Resource DetectedIssueEvidenceComponent = ResourceFactory
			.createResource(NS + "DetectedIssueEvidenceComponent");
	public static final Resource DetectedIssueMitigationComponent = ResourceFactory
			.createResource(NS + "DetectedIssueMitigationComponent");
	public static final Resource Device = ResourceFactory.createResource(NS + "Device");
	public static final Resource DeviceAssociation = ResourceFactory.createResource(NS + "DeviceAssociation");
	public static final Resource DeviceAssociationOperationComponent = ResourceFactory
			.createResource(NS + "DeviceAssociationOperationComponent");
	public static final Resource DeviceConformsToComponent = ResourceFactory
			.createResource(NS + "DeviceConformsToComponent");
	public static final Resource DeviceDefinition = ResourceFactory.createResource(NS + "DeviceDefinition");
	public static final Resource DeviceDefinitionChargeItemComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionChargeItemComponent");
	public static final Resource DeviceDefinitionClassificationComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionClassificationComponent");
	public static final Resource DeviceDefinitionConformsToComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionConformsToComponent");
	public static final Resource DeviceDefinitionCorrectiveActionComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionCorrectiveActionComponent");
	public static final Resource DeviceDefinitionDeviceNameComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionDeviceNameComponent");
	public static final Resource DeviceDefinitionGuidelineComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionGuidelineComponent");
	public static final Resource DeviceDefinitionHasPartComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionHasPartComponent");
	public static final Resource DeviceDefinitionLinkComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionLinkComponent");
	public static final Resource DeviceDefinitionMaterialComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionMaterialComponent");
	public static final Resource DeviceDefinitionPackagingComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionPackagingComponent");
	public static final Resource DeviceDefinitionPackagingComponentPackaging = ResourceFactory
			.createResource(NS + "DeviceDefinitionPackagingComponentPackaging");
	public static final Resource DeviceDefinitionPackagingComponentPackagingPackaging = ResourceFactory
			.createResource(NS + "DeviceDefinitionPackagingComponentPackagingPackaging");
	public static final Resource DeviceDefinitionPackagingComponentPackagingUdiDeviceIdentifier = ResourceFactory
			.createResource(NS + "DeviceDefinitionPackagingComponentPackagingUdiDeviceIdentifier");
	public static final Resource DeviceDefinitionPackagingComponentUdiDeviceIdentifier = ResourceFactory
			.createResource(NS + "DeviceDefinitionPackagingComponentUdiDeviceIdentifier");
	public static final Resource DeviceDefinitionPropertyComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionPropertyComponent");
	public static final Resource DeviceDefinitionRegulatoryIdentifierComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionRegulatoryIdentifierComponent");
	public static final Resource DeviceDefinitionUdiDeviceIdentifierComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionUdiDeviceIdentifierComponent");
	public static final Resource DeviceDefinitionVersionComponent = ResourceFactory
			.createResource(NS + "DeviceDefinitionVersionComponent");
	public static final Resource DeviceDispense = ResourceFactory.createResource(NS + "DeviceDispense");
	public static final Resource DeviceDispensePerformerComponent = ResourceFactory
			.createResource(NS + "DeviceDispensePerformerComponent");
	public static final Resource DeviceMetric = ResourceFactory.createResource(NS + "DeviceMetric");
	public static final Resource DeviceMetricCalibrationComponent = ResourceFactory
			.createResource(NS + "DeviceMetricCalibrationComponent");
	public static final Resource DeviceNameComponent = ResourceFactory.createResource(NS + "DeviceNameComponent");
	public static final Resource DevicePropertyComponent = ResourceFactory
			.createResource(NS + "DevicePropertyComponent");
	public static final Resource DeviceRequest = ResourceFactory.createResource(NS + "DeviceRequest");
	public static final Resource DeviceRequestParameterComponent = ResourceFactory
			.createResource(NS + "DeviceRequestParameterComponent");
	public static final Resource DeviceUdiCarrierComponent = ResourceFactory
			.createResource(NS + "DeviceUdiCarrierComponent");
	public static final Resource DeviceUsage = ResourceFactory.createResource(NS + "DeviceUsage");
	public static final Resource DeviceUsageAdherenceComponent = ResourceFactory
			.createResource(NS + "DeviceUsageAdherenceComponent");
	public static final Resource DeviceVersionComponent = ResourceFactory.createResource(NS + "DeviceVersionComponent");
	public static final Resource Diagnosis = ResourceFactory.createResource(NS + "Diagnosis");
	public static final Resource DiagnosticReport = ResourceFactory.createResource(NS + "DiagnosticReport");
	public static final Resource DiagnosticReportMediaComponent = ResourceFactory
			.createResource(NS + "DiagnosticReportMediaComponent");
	public static final Resource DiagnosticReportSupportingInfoComponent = ResourceFactory
			.createResource(NS + "DiagnosticReportSupportingInfoComponent");
	public static final Resource Distance = ResourceFactory.createResource(NS + "Distance");
	public static final Resource DocumentReference = ResourceFactory.createResource(NS + "DocumentReference");
	public static final Resource DocumentReferenceAttesterComponent = ResourceFactory
			.createResource(NS + "DocumentReferenceAttesterComponent");
	public static final Resource DocumentReferenceContentComponent = ResourceFactory
			.createResource(NS + "DocumentReferenceContentComponent");
	public static final Resource DocumentReferenceContentProfileComponent = ResourceFactory
			.createResource(NS + "DocumentReferenceContentProfileComponent");
	public static final Resource DocumentReferenceRelatesToComponent = ResourceFactory
			.createResource(NS + "DocumentReferenceRelatesToComponent");
	public static final Resource DomainResource = ResourceFactory.createResource(NS + "DomainResource");
	public static final Resource Dosage = ResourceFactory.createResource(NS + "Dosage");
	public static final Resource DosageDoseAndRateComponent = ResourceFactory
			.createResource(NS + "DosageDoseAndRateComponent");
	public static final Resource Duration = ResourceFactory.createResource(NS + "Duration");
	public static final Resource Element = ResourceFactory.createResource(NS + "Element");
	public static final Resource ElementDefinition = ResourceFactory.createResource(NS + "ElementDefinition");
	public static final Resource ElementDefinitionBaseComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionBaseComponent");
	public static final Resource ElementDefinitionBindingAdditionalComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionBindingAdditionalComponent");
	public static final Resource ElementDefinitionBindingComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionBindingComponent");
	public static final Resource ElementDefinitionConstraintComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionConstraintComponent");
	public static final Resource ElementDefinitionExampleComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionExampleComponent");
	public static final Resource ElementDefinitionMappingComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionMappingComponent");
	public static final Resource ElementDefinitionSlicingComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionSlicingComponent");
	public static final Resource ElementDefinitionSlicingDiscriminatorComponent = ResourceFactory
			.createResource(NS + "ElementDefinitionSlicingDiscriminatorComponent");
	public static final Resource Encounter = ResourceFactory.createResource(NS + "Encounter");
	public static final Resource EncounterAdmissionComponent = ResourceFactory
			.createResource(NS + "EncounterAdmissionComponent");
	public static final Resource EncounterHistory = ResourceFactory.createResource(NS + "EncounterHistory");
	public static final Resource EncounterHistoryLocationComponent = ResourceFactory
			.createResource(NS + "EncounterHistoryLocationComponent");
	public static final Resource EncounterLocationComponent = ResourceFactory
			.createResource(NS + "EncounterLocationComponent");
	public static final Resource EncounterParticipantComponent = ResourceFactory
			.createResource(NS + "EncounterParticipantComponent");
	public static final Resource Endpoint = ResourceFactory.createResource(NS + "Endpoint");
	public static final Resource EndpointPayloadComponent = ResourceFactory
			.createResource(NS + "EndpointPayloadComponent");
	public static final Resource EnrollmentRequest = ResourceFactory.createResource(NS + "EnrollmentRequest");
	public static final Resource EnrollmentResponse = ResourceFactory.createResource(NS + "EnrollmentResponse");
	public static final Resource EnteralFormulaSchedule = ResourceFactory.createResource(NS + "EnteralFormulaSchedule");
	public static final Resource EpisodeOfCare = ResourceFactory.createResource(NS + "EpisodeOfCare");
	public static final Resource EpisodeOfCareStatusHistoryComponent = ResourceFactory
			.createResource(NS + "EpisodeOfCareStatusHistoryComponent");
	public static final Resource Error = ResourceFactory.createResource(NS + "Error");
	public static final Resource Errors = ResourceFactory.createResource(NS + "Errors");
	public static final Resource EventDefinition = ResourceFactory.createResource(NS + "EventDefinition");
	public static final Resource Evidence = ResourceFactory.createResource(NS + "Evidence");
	public static final Resource EvidenceCertaintyComponent = ResourceFactory
			.createResource(NS + "EvidenceCertaintyComponent");
	public static final Resource EvidenceCertaintyComponentCertainty = ResourceFactory
			.createResource(NS + "EvidenceCertaintyComponentCertainty");
	public static final Resource EvidenceCertaintyComponentCertaintyCertainty = ResourceFactory
			.createResource(NS + "EvidenceCertaintyComponentCertaintyCertainty");
	public static final Resource EvidenceReport = ResourceFactory.createResource(NS + "EvidenceReport");
	public static final Resource EvidenceReport_SectionSection = ResourceFactory
			.createResource(NS + "EvidenceReport.SectionSection");
	public static final Resource EvidenceReport_SectionSectionSection = ResourceFactory
			.createResource(NS + "EvidenceReport.SectionSectionSection");
	public static final Resource EvidenceReportRelatesToComponent = ResourceFactory
			.createResource(NS + "EvidenceReportRelatesToComponent");
	public static final Resource EvidenceReportRelatesToTargetComponent = ResourceFactory
			.createResource(NS + "EvidenceReportRelatesToTargetComponent");
	public static final Resource EvidenceReportSubjectCharacteristicComponent = ResourceFactory
			.createResource(NS + "EvidenceReportSubjectCharacteristicComponent");
	public static final Resource EvidenceReportSubjectComponent = ResourceFactory
			.createResource(NS + "EvidenceReportSubjectComponent");
	public static final Resource EvidenceStatisticAttributeEstimateComponent = ResourceFactory
			.createResource(NS + "EvidenceStatisticAttributeEstimateComponent");
	public static final Resource EvidenceStatisticComponent = ResourceFactory
			.createResource(NS + "EvidenceStatisticComponent");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticAttributeEstimateComponentAttributeEstimate = ResourceFactory
			.createResource(
					NS + "EvidenceStatisticComponent.EvidenceStatisticAttributeEstimateComponentAttributeEstimate");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticAttributeEstimateComponentAttributeEstimateAttributeEstimate = ResourceFactory
			.createResource(NS
					+ "EvidenceStatisticComponent.EvidenceStatisticAttributeEstimateComponentAttributeEstimateAttributeEstimate");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticModelCharacteristicComponentAttributeEstimate = ResourceFactory
			.createResource(
					NS + "EvidenceStatisticComponent.EvidenceStatisticModelCharacteristicComponentAttributeEstimate");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticModelCharacteristicComponentAttributeEstimateAttributeEstimate = ResourceFactory
			.createResource(NS
					+ "EvidenceStatisticComponent.EvidenceStatisticModelCharacteristicComponentAttributeEstimateAttributeEstimate");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticModelCharacteristicComponentModelCharacteristic = ResourceFactory
			.createResource(
					NS + "EvidenceStatisticComponent.EvidenceStatisticModelCharacteristicComponentModelCharacteristic");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticModelCharacteristicComponentModelCharacteristicAttributeEstimate = ResourceFactory
			.createResource(NS
					+ "EvidenceStatisticComponent.EvidenceStatisticModelCharacteristicComponentModelCharacteristicAttributeEstimate");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticModelCharacteristicComponentModelCharacteristicAttributeEstimateAttributeEstimate = ResourceFactory
			.createResource(NS
					+ "EvidenceStatisticComponent.EvidenceStatisticModelCharacteristicComponentModelCharacteristicAttributeEstimateAttributeEstimate");
	public static final Resource EvidenceStatisticComponent_EvidenceStatisticModelCharacteristicComponentModelCharacteristicModelCharacteristic = ResourceFactory
			.createResource(NS
					+ "EvidenceStatisticComponent.EvidenceStatisticModelCharacteristicComponentModelCharacteristicModelCharacteristic");
	public static final Resource EvidenceStatisticModelCharacteristicComponent = ResourceFactory
			.createResource(NS + "EvidenceStatisticModelCharacteristicComponent");
	public static final Resource EvidenceStatisticModelCharacteristicVariableComponent = ResourceFactory
			.createResource(NS + "EvidenceStatisticModelCharacteristicVariableComponent");
	public static final Resource EvidenceStatisticSampleSizeComponent = ResourceFactory
			.createResource(NS + "EvidenceStatisticSampleSizeComponent");
	public static final Resource EvidenceVariable = ResourceFactory.createResource(NS + "EvidenceVariable");
	public static final Resource EvidenceVariableCategoryComponent = ResourceFactory
			.createResource(NS + "EvidenceVariableCategoryComponent");
	public static final Resource EvidenceVariableCharacteristicComponent = ResourceFactory
			.createResource(NS + "EvidenceVariableCharacteristicComponent");
	public static final Resource EvidenceVariableCharacteristicComponent_EvidenceVariableCharacteristicDefinitionByCombinationComponentCharacteristic = ResourceFactory
			.createResource(NS
					+ "EvidenceVariableCharacteristicComponent.EvidenceVariableCharacteristicDefinitionByCombinationComponentCharacteristic");
	public static final Resource EvidenceVariableCharacteristicComponent_EvidenceVariableCharacteristicDefinitionByCombinationComponentCharacteristic_EvidenceVariableCharacteristicDefinitionByCombinationComponentCharacteristic = ResourceFactory
			.createResource(NS
					+ "EvidenceVariableCharacteristicComponent.EvidenceVariableCharacteristicDefinitionByCombinationComponentCharacteristic.EvidenceVariableCharacteristicDefinitionByCombinationComponentCharacteristic");
	public static final Resource EvidenceVariableCharacteristicDefinitionByCombinationComponent = ResourceFactory
			.createResource(NS + "EvidenceVariableCharacteristicDefinitionByCombinationComponent");
	public static final Resource EvidenceVariableCharacteristicDefinitionByTypeAndValueComponent = ResourceFactory
			.createResource(NS + "EvidenceVariableCharacteristicDefinitionByTypeAndValueComponent");
	public static final Resource EvidenceVariableCharacteristicTimeFromEventComponent = ResourceFactory
			.createResource(NS + "EvidenceVariableCharacteristicTimeFromEventComponent");
	public static final Resource EvidenceVariableDefinitionComponent = ResourceFactory
			.createResource(NS + "EvidenceVariableDefinitionComponent");
	public static final Resource ExampleScenario = ResourceFactory.createResource(NS + "ExampleScenario");
	public static final Resource ExampleScenarioActorComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioActorComponent");
	public static final Resource ExampleScenarioInstanceComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioInstanceComponent");
	public static final Resource ExampleScenarioInstanceContainedInstanceComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioInstanceContainedInstanceComponent");
	public static final Resource ExampleScenarioInstanceVersionComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioInstanceVersionComponent");
	public static final Resource ExampleScenarioProcessComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioProcessComponent");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep_ExampleScenarioProcessStepAlternativeComponentStep = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep.ExampleScenarioProcessStepAlternativeComponentStep");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep_ExampleScenarioProcessStepOperationComponentContainedInstance = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep.ExampleScenarioProcessStepOperationComponentContainedInstance");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStepProcess = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStepProcess");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStepProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStepProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStepProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepOperationComponentContainedInstance = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStepProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepOperationComponentContainedInstance");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStepProcess_ExampleScenarioProcessStepComponentProcess = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStepProcess.ExampleScenarioProcessStepComponentProcess");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepOperationComponentContainedInstance = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepOperationComponentContainedInstance");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess = ResourceFactory
			.createResource(NS + "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep_ExampleScenarioProcessStepAlternativeComponentStep = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep.ExampleScenarioProcessStepAlternativeComponentStep");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStep_ExampleScenarioProcessStepOperationComponentContainedInstance = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStep.ExampleScenarioProcessStepOperationComponentContainedInstance");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepAlternativeComponentStepProcess = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepAlternativeComponentStepProcess");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess_ExampleScenarioProcessStepComponent_ExampleScenarioProcessStepOperationComponentContainedInstance = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess.ExampleScenarioProcessStepComponent.ExampleScenarioProcessStepOperationComponentContainedInstance");
	public static final Resource ExampleScenarioProcessComponent_ExampleScenarioProcessStepComponentProcess_ExampleScenarioProcessStepComponentProcess = ResourceFactory
			.createResource(NS
					+ "ExampleScenarioProcessComponent.ExampleScenarioProcessStepComponentProcess.ExampleScenarioProcessStepComponentProcess");
	public static final Resource ExampleScenarioProcessStepAlternativeComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioProcessStepAlternativeComponent");
	public static final Resource ExampleScenarioProcessStepComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioProcessStepComponent");
	public static final Resource ExampleScenarioProcessStepOperationComponent = ResourceFactory
			.createResource(NS + "ExampleScenarioProcessStepOperationComponent");
	public static final Resource Exemption = ResourceFactory.createResource(NS + "Exemption");
	public static final Resource ExplanationOfBenefit = ResourceFactory.createResource(NS + "ExplanationOfBenefit");
	public static final Resource ExplanationOfBenefit_AddedItem_AddedItemDetail_AddedItemDetailSubDetailAdjudication = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.AddedItem.AddedItemDetail.AddedItemDetailSubDetailAdjudication");
	public static final Resource ExplanationOfBenefit_AddedItem_AddedItemDetail_AddedItemDetailSubDetailReviewOutcome = ResourceFactory
			.createResource(
					NS + "ExplanationOfBenefit.AddedItem.AddedItemDetail.AddedItemDetailSubDetailReviewOutcome");
	public static final Resource ExplanationOfBenefit_AddedItem_AddedItemDetailAdjudication = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.AddedItem.AddedItemDetailAdjudication");
	public static final Resource ExplanationOfBenefit_AddedItem_AddedItemDetailReviewOutcome = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.AddedItem.AddedItemDetailReviewOutcome");
	public static final Resource ExplanationOfBenefit_AddedItemAdjudication = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.AddedItemAdjudication");
	public static final Resource ExplanationOfBenefit_AddedItemReviewOutcome = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.AddedItemReviewOutcome");
	public static final Resource ExplanationOfBenefit_Item_Detail_SubDetailAdjudication = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.Item.Detail.SubDetailAdjudication");
	public static final Resource ExplanationOfBenefit_Item_Detail_SubDetailReviewOutcome = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.Item.Detail.SubDetailReviewOutcome");
	public static final Resource ExplanationOfBenefit_Item_DetailAdjudication = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.Item.DetailAdjudication");
	public static final Resource ExplanationOfBenefit_Item_DetailReviewOutcome = ResourceFactory
			.createResource(NS + "ExplanationOfBenefit.Item.DetailReviewOutcome");
	public static final Resource ExplanationOfBenefitAdjudication = ResourceFactory
			.createResource(NS + "ExplanationOfBenefitAdjudication");
	public static final Resource ExplanationOfBenefitEventComponent = ResourceFactory
			.createResource(NS + "ExplanationOfBenefitEventComponent");
	public static final Resource ExplanationOfBenefitItemReviewOutcomeComponent = ResourceFactory
			.createResource(NS + "ExplanationOfBenefitItemReviewOutcomeComponent");
	public static final Resource Expression = ResourceFactory.createResource(NS + "Expression");
	public static final Resource ExtendedContactDetail = ResourceFactory.createResource(NS + "ExtendedContactDetail");
	public static final Resource Extension = ResourceFactory.createResource(NS + "Extension");
	public static final Resource FamilyMemberHistory = ResourceFactory.createResource(NS + "FamilyMemberHistory");
	public static final Resource FamilyMemberHistoryConditionComponent = ResourceFactory
			.createResource(NS + "FamilyMemberHistoryConditionComponent");
	public static final Resource FamilyMemberHistoryParticipantComponent = ResourceFactory
			.createResource(NS + "FamilyMemberHistoryParticipantComponent");
	public static final Resource FamilyMemberHistoryProcedureComponent = ResourceFactory
			.createResource(NS + "FamilyMemberHistoryProcedureComponent");
	public static final Resource Flag = ResourceFactory.createResource(NS + "Flag");
	public static final Resource FormularyItem = ResourceFactory.createResource(NS + "FormularyItem");
	public static final Resource FriendlyLanguage = ResourceFactory.createResource(NS + "FriendlyLanguage");
	public static final Resource GenomicStudy = ResourceFactory.createResource(NS + "GenomicStudy");
	public static final Resource GenomicStudyAnalysisComponent = ResourceFactory
			.createResource(NS + "GenomicStudyAnalysisComponent");
	public static final Resource GenomicStudyAnalysisDeviceComponent = ResourceFactory
			.createResource(NS + "GenomicStudyAnalysisDeviceComponent");
	public static final Resource GenomicStudyAnalysisInputComponent = ResourceFactory
			.createResource(NS + "GenomicStudyAnalysisInputComponent");
	public static final Resource GenomicStudyAnalysisOutputComponent = ResourceFactory
			.createResource(NS + "GenomicStudyAnalysisOutputComponent");
	public static final Resource GenomicStudyAnalysisPerformerComponent = ResourceFactory
			.createResource(NS + "GenomicStudyAnalysisPerformerComponent");
	public static final Resource Goal = ResourceFactory.createResource(NS + "Goal");
	public static final Resource GoalTargetComponent = ResourceFactory.createResource(NS + "GoalTargetComponent");
	public static final Resource GraphDefinition = ResourceFactory.createResource(NS + "GraphDefinition");
	public static final Resource GraphDefinitionLinkCompartmentComponent = ResourceFactory
			.createResource(NS + "GraphDefinitionLinkCompartmentComponent");
	public static final Resource GraphDefinitionLinkComponent = ResourceFactory
			.createResource(NS + "GraphDefinitionLinkComponent");
	public static final Resource GraphDefinitionNodeComponent = ResourceFactory
			.createResource(NS + "GraphDefinitionNodeComponent");
	public static final Resource Group = ResourceFactory.createResource(NS + "Group");
	public static final Resource GroupCharacteristicComponent = ResourceFactory
			.createResource(NS + "GroupCharacteristicComponent");
	public static final Resource GroupMemberComponent = ResourceFactory.createResource(NS + "GroupMemberComponent");
	public static final Resource Guarantor = ResourceFactory.createResource(NS + "Guarantor");
	public static final Resource GuidanceResponse = ResourceFactory.createResource(NS + "GuidanceResponse");
	public static final Resource HealthcareService = ResourceFactory.createResource(NS + "HealthcareService");
	public static final Resource HealthcareServiceEligibilityComponent = ResourceFactory
			.createResource(NS + "HealthcareServiceEligibilityComponent");
	public static final Resource HumanName = ResourceFactory.createResource(NS + "HumanName");
	public static final Resource Identifier = ResourceFactory.createResource(NS + "Identifier");
	public static final Resource ImageRegion2D = ResourceFactory.createResource(NS + "ImageRegion2D");
	public static final Resource ImageRegion3D = ResourceFactory.createResource(NS + "ImageRegion3D");
	public static final Resource ImagingSelection = ResourceFactory.createResource(NS + "ImagingSelection");
	public static final Resource ImagingSelectionInstanceComponent = ResourceFactory
			.createResource(NS + "ImagingSelectionInstanceComponent");
	public static final Resource ImagingSelectionPerformerComponent = ResourceFactory
			.createResource(NS + "ImagingSelectionPerformerComponent");
	public static final Resource ImagingStudy = ResourceFactory.createResource(NS + "ImagingStudy");
	public static final Resource ImagingStudySeriesComponent = ResourceFactory
			.createResource(NS + "ImagingStudySeriesComponent");
	public static final Resource ImagingStudySeriesInstanceComponent = ResourceFactory
			.createResource(NS + "ImagingStudySeriesInstanceComponent");
	public static final Resource ImagingStudySeriesPerformerComponent = ResourceFactory
			.createResource(NS + "ImagingStudySeriesPerformerComponent");
	public static final Resource Immunization = ResourceFactory.createResource(NS + "Immunization");
	public static final Resource ImmunizationEvaluation = ResourceFactory.createResource(NS + "ImmunizationEvaluation");
	public static final Resource ImmunizationPerformerComponent = ResourceFactory
			.createResource(NS + "ImmunizationPerformerComponent");
	public static final Resource ImmunizationProgramEligibilityComponent = ResourceFactory
			.createResource(NS + "ImmunizationProgramEligibilityComponent");
	public static final Resource ImmunizationProtocolAppliedComponent = ResourceFactory
			.createResource(NS + "ImmunizationProtocolAppliedComponent");
	public static final Resource ImmunizationReactionComponent = ResourceFactory
			.createResource(NS + "ImmunizationReactionComponent");
	public static final Resource ImmunizationRecommendation = ResourceFactory
			.createResource(NS + "ImmunizationRecommendation");
	public static final Resource ImmunizationRecommendationRecommendationComponent = ResourceFactory
			.createResource(NS + "ImmunizationRecommendationRecommendationComponent");
	public static final Resource ImmunizationRecommendationRecommendationDateCriterionComponent = ResourceFactory
			.createResource(NS + "ImmunizationRecommendationRecommendationDateCriterionComponent");
	public static final Resource ImplementationGuide = ResourceFactory.createResource(NS + "ImplementationGuide");
	public static final Resource ImplementationGuideDefinitionComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDefinitionComponent");
	public static final Resource ImplementationGuideDefinitionComponent_ImplementationGuideDefinitionPageComponentPage = ResourceFactory
			.createResource(
					NS + "ImplementationGuideDefinitionComponent.ImplementationGuideDefinitionPageComponentPage");
	public static final Resource ImplementationGuideDefinitionComponent_ImplementationGuideDefinitionPageComponentPagePage = ResourceFactory
			.createResource(
					NS + "ImplementationGuideDefinitionComponent.ImplementationGuideDefinitionPageComponentPagePage");
	public static final Resource ImplementationGuideDefinitionGroupingComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDefinitionGroupingComponent");
	public static final Resource ImplementationGuideDefinitionPageComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDefinitionPageComponent");
	public static final Resource ImplementationGuideDefinitionParameterComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDefinitionParameterComponent");
	public static final Resource ImplementationGuideDefinitionResourceComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDefinitionResourceComponent");
	public static final Resource ImplementationGuideDefinitionTemplateComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDefinitionTemplateComponent");
	public static final Resource ImplementationGuideDependsOnComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideDependsOnComponent");
	public static final Resource ImplementationGuideGlobalComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideGlobalComponent");
	public static final Resource ImplementationGuideManifestComponent = ResourceFactory
			.createResource(NS + "ImplementationGuideManifestComponent");
	public static final Resource Ingredient = ResourceFactory.createResource(NS + "Ingredient");
	public static final Resource IngredientManufacturerComponent = ResourceFactory
			.createResource(NS + "IngredientManufacturerComponent");
	public static final Resource IngredientSubstanceComponent = ResourceFactory
			.createResource(NS + "IngredientSubstanceComponent");
	public static final Resource IngredientSubstanceStrengthComponent = ResourceFactory
			.createResource(NS + "IngredientSubstanceStrengthComponent");
	public static final Resource IngredientSubstanceStrengthReferenceStrengthComponent = ResourceFactory
			.createResource(NS + "IngredientSubstanceStrengthReferenceStrengthComponent");
	public static final Resource Insurance = ResourceFactory.createResource(NS + "Insurance");
	public static final Resource InsurancePlan = ResourceFactory.createResource(NS + "InsurancePlan");
	public static final Resource InsurancePlanCoverageBenefitLimitComponent = ResourceFactory
			.createResource(NS + "InsurancePlanCoverageBenefitLimitComponent");
	public static final Resource InsurancePlanCoverageComponent = ResourceFactory
			.createResource(NS + "InsurancePlanCoverageComponent");
	public static final Resource InsurancePlanPlanComponent = ResourceFactory
			.createResource(NS + "InsurancePlanPlanComponent");
	public static final Resource InsurancePlanPlanGeneralCostComponent = ResourceFactory
			.createResource(NS + "InsurancePlanPlanGeneralCostComponent");
	public static final Resource InsurancePlanPlanSpecificCostBenefitCostComponent = ResourceFactory
			.createResource(NS + "InsurancePlanPlanSpecificCostBenefitCostComponent");
	public static final Resource InsurancePlanPlanSpecificCostComponent = ResourceFactory
			.createResource(NS + "InsurancePlanPlanSpecificCostComponent");
	public static final Resource InventoryItem = ResourceFactory.createResource(NS + "InventoryItem");
	public static final Resource InventoryItemAssociationComponent = ResourceFactory
			.createResource(NS + "InventoryItemAssociationComponent");
	public static final Resource InventoryItemCharacteristicComponent = ResourceFactory
			.createResource(NS + "InventoryItemCharacteristicComponent");
	public static final Resource InventoryItemDescriptionComponent = ResourceFactory
			.createResource(NS + "InventoryItemDescriptionComponent");
	public static final Resource InventoryItemInstanceComponent = ResourceFactory
			.createResource(NS + "InventoryItemInstanceComponent");
	public static final Resource InventoryItemNameComponent = ResourceFactory
			.createResource(NS + "InventoryItemNameComponent");
	public static final Resource InventoryItemResponsibleOrganizationComponent = ResourceFactory
			.createResource(NS + "InventoryItemResponsibleOrganizationComponent");
	public static final Resource InventoryReport = ResourceFactory.createResource(NS + "InventoryReport");
	public static final Resource InventoryReportInventoryListingComponent = ResourceFactory
			.createResource(NS + "InventoryReportInventoryListingComponent");
	public static final Resource InventoryReportInventoryListingItemComponent = ResourceFactory
			.createResource(NS + "InventoryReportInventoryListingItemComponent");
	public static final Resource Invoice = ResourceFactory.createResource(NS + "Invoice");
	public static final Resource InvoiceLineItemComponent = ResourceFactory
			.createResource(NS + "InvoiceLineItemComponent");
	public static final Resource InvoiceParticipantComponent = ResourceFactory
			.createResource(NS + "InvoiceParticipantComponent");
	public static final Resource Item = ResourceFactory.createResource(NS + "Item");
	public static final Resource ItemBodySite = ResourceFactory.createResource(NS + "ItemBodySite");
	public static final Resource ItemDetail = ResourceFactory.createResource(NS + "ItemDetail");
	public static final Resource Items = ResourceFactory.createResource(NS + "Items");
	public static final Resource LegalLanguage = ResourceFactory.createResource(NS + "LegalLanguage");
	public static final Resource Library = ResourceFactory.createResource(NS + "Library");
	public static final Resource Linkage = ResourceFactory.createResource(NS + "Linkage");
	public static final Resource LinkageItemComponent = ResourceFactory.createResource(NS + "LinkageItemComponent");
	public static final Resource List = ResourceFactory.createResource(NS + "List");
	public static final Resource ListEntryComponent = ResourceFactory.createResource(NS + "ListEntryComponent");
	public static final Resource Location = ResourceFactory.createResource(NS + "Location");
	public static final Resource LocationPositionComponent = ResourceFactory
			.createResource(NS + "LocationPositionComponent");
	public static final Resource ManifestPage = ResourceFactory.createResource(NS + "ManifestPage");
	public static final Resource ManifestResource = ResourceFactory.createResource(NS + "ManifestResource");
	public static final Resource ManufacturedItemDefinition = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinition");
	public static final Resource ManufacturedItemDefinitionComponentComponent = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionComponentComponent");
	public static final Resource ManufacturedItemDefinitionComponentComponentComponent = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionComponentComponentComponent");
	public static final Resource ManufacturedItemDefinitionComponentComponentComponentComponent = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionComponentComponentComponentComponent");
	public static final Resource ManufacturedItemDefinitionComponentComponentComponentProperty = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionComponentComponentComponentProperty");
	public static final Resource ManufacturedItemDefinitionComponentComponentProperty = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionComponentComponentProperty");
	public static final Resource ManufacturedItemDefinitionComponentConstituentComponent = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionComponentConstituentComponent");
	public static final Resource ManufacturedItemDefinitionPropertyComponent = ResourceFactory
			.createResource(NS + "ManufacturedItemDefinitionPropertyComponent");
	public static final Resource MappingProperty = ResourceFactory.createResource(NS + "MappingProperty");
	public static final Resource MarketingStatus = ResourceFactory.createResource(NS + "MarketingStatus");
	public static final Resource Measure = ResourceFactory.createResource(NS + "Measure");
	public static final Resource MeasureGroupComponent = ResourceFactory.createResource(NS + "MeasureGroupComponent");
	public static final Resource MeasureGroupPopulationComponent = ResourceFactory
			.createResource(NS + "MeasureGroupPopulationComponent");
	public static final Resource MeasureGroupStratifierComponent = ResourceFactory
			.createResource(NS + "MeasureGroupStratifierComponent");
	public static final Resource MeasureGroupStratifierComponentComponent = ResourceFactory
			.createResource(NS + "MeasureGroupStratifierComponentComponent");
	public static final Resource MeasureReport = ResourceFactory.createResource(NS + "MeasureReport");
	public static final Resource MeasureReportGroupComponent = ResourceFactory
			.createResource(NS + "MeasureReportGroupComponent");
	public static final Resource MeasureReportGroupPopulationComponent = ResourceFactory
			.createResource(NS + "MeasureReportGroupPopulationComponent");
	public static final Resource MeasureReportGroupStratifierComponent = ResourceFactory
			.createResource(NS + "MeasureReportGroupStratifierComponent");
	public static final Resource MeasureReportGroupStratifierStratumComponentComponent = ResourceFactory
			.createResource(NS + "MeasureReportGroupStratifierStratumComponentComponent");
	public static final Resource MeasureSupplementalDataComponent = ResourceFactory
			.createResource(NS + "MeasureSupplementalDataComponent");
	public static final Resource MeasureTermComponent = ResourceFactory.createResource(NS + "MeasureTermComponent");
	public static final Resource Medication = ResourceFactory.createResource(NS + "Medication");
	public static final Resource MedicationAdministration = ResourceFactory
			.createResource(NS + "MedicationAdministration");
	public static final Resource MedicationAdministrationDosageComponent = ResourceFactory
			.createResource(NS + "MedicationAdministrationDosageComponent");
	public static final Resource MedicationAdministrationPerformerComponent = ResourceFactory
			.createResource(NS + "MedicationAdministrationPerformerComponent");
	public static final Resource MedicationBatchComponent = ResourceFactory
			.createResource(NS + "MedicationBatchComponent");
	public static final Resource MedicationDispense = ResourceFactory.createResource(NS + "MedicationDispense");
	public static final Resource MedicationDispensePerformerComponent = ResourceFactory
			.createResource(NS + "MedicationDispensePerformerComponent");
	public static final Resource MedicationDispenseSubstitutionComponent = ResourceFactory
			.createResource(NS + "MedicationDispenseSubstitutionComponent");
	public static final Resource MedicationIngredientComponent = ResourceFactory
			.createResource(NS + "MedicationIngredientComponent");
	public static final Resource MedicationKnowledge = ResourceFactory.createResource(NS + "MedicationKnowledge");
	public static final Resource MedicationKnowledgeCostComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeCostComponent");
	public static final Resource MedicationKnowledgeDefinitionalComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeDefinitionalComponent");
	public static final Resource MedicationKnowledgeDefinitionalDrugCharacteristicComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeDefinitionalDrugCharacteristicComponent");
	public static final Resource MedicationKnowledgeDefinitionalIngredientComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeDefinitionalIngredientComponent");
	public static final Resource MedicationKnowledgeIndicationGuidelineComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeIndicationGuidelineComponent");
	public static final Resource MedicationKnowledgeIndicationGuidelineDosingGuidelineComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeIndicationGuidelineDosingGuidelineComponent");
	public static final Resource MedicationKnowledgeIndicationGuidelineDosingGuidelineDosageComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeIndicationGuidelineDosingGuidelineDosageComponent");
	public static final Resource MedicationKnowledgeIndicationGuidelineDosingGuidelinePatientCharacteristicComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeIndicationGuidelineDosingGuidelinePatientCharacteristicComponent");
	public static final Resource MedicationKnowledgeMedicineClassificationComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeMedicineClassificationComponent");
	public static final Resource MedicationKnowledgeMonitoringProgramComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeMonitoringProgramComponent");
	public static final Resource MedicationKnowledgeMonographComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeMonographComponent");
	public static final Resource MedicationKnowledgePackagingComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgePackagingComponent");
	public static final Resource MedicationKnowledgePackagingComponentCost = ResourceFactory
			.createResource(NS + "MedicationKnowledgePackagingComponentCost");
	public static final Resource MedicationKnowledgeRegulatoryComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeRegulatoryComponent");
	public static final Resource MedicationKnowledgeRegulatoryMaxDispenseComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeRegulatoryMaxDispenseComponent");
	public static final Resource MedicationKnowledgeRegulatorySubstitutionComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeRegulatorySubstitutionComponent");
	public static final Resource MedicationKnowledgeRelatedMedicationKnowledgeComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeRelatedMedicationKnowledgeComponent");
	public static final Resource MedicationKnowledgeStorageGuidelineComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeStorageGuidelineComponent");
	public static final Resource MedicationKnowledgeStorageGuidelineEnvironmentalSettingComponent = ResourceFactory
			.createResource(NS + "MedicationKnowledgeStorageGuidelineEnvironmentalSettingComponent");
	public static final Resource MedicationRequest = ResourceFactory.createResource(NS + "MedicationRequest");
	public static final Resource MedicationRequestDispenseRequestComponent = ResourceFactory
			.createResource(NS + "MedicationRequestDispenseRequestComponent");
	public static final Resource MedicationRequestDispenseRequestInitialFillComponent = ResourceFactory
			.createResource(NS + "MedicationRequestDispenseRequestInitialFillComponent");
	public static final Resource MedicationRequestSubstitutionComponent = ResourceFactory
			.createResource(NS + "MedicationRequestSubstitutionComponent");
	public static final Resource MedicationStatement = ResourceFactory.createResource(NS + "MedicationStatement");
	public static final Resource MedicationStatementAdherenceComponent = ResourceFactory
			.createResource(NS + "MedicationStatementAdherenceComponent");
	public static final Resource MedicinalProductDefinition = ResourceFactory
			.createResource(NS + "MedicinalProductDefinition");
	public static final Resource MedicinalProductDefinitionCharacteristicComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionCharacteristicComponent");
	public static final Resource MedicinalProductDefinitionContactComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionContactComponent");
	public static final Resource MedicinalProductDefinitionCrossReferenceComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionCrossReferenceComponent");
	public static final Resource MedicinalProductDefinitionNameComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionNameComponent");
	public static final Resource MedicinalProductDefinitionNamePartComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionNamePartComponent");
	public static final Resource MedicinalProductDefinitionNameUsageComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionNameUsageComponent");
	public static final Resource MedicinalProductDefinitionOperationComponent = ResourceFactory
			.createResource(NS + "MedicinalProductDefinitionOperationComponent");
	public static final Resource MessageDefinition = ResourceFactory.createResource(NS + "MessageDefinition");
	public static final Resource MessageDefinitionAllowedResponseComponent = ResourceFactory
			.createResource(NS + "MessageDefinitionAllowedResponseComponent");
	public static final Resource MessageDefinitionFocusComponent = ResourceFactory
			.createResource(NS + "MessageDefinitionFocusComponent");
	public static final Resource MessageDestination = ResourceFactory.createResource(NS + "MessageDestination");
	public static final Resource MessageHeader = ResourceFactory.createResource(NS + "MessageHeader");
	public static final Resource MessageHeaderResponseComponent = ResourceFactory
			.createResource(NS + "MessageHeaderResponseComponent");
	public static final Resource MessageSource = ResourceFactory.createResource(NS + "MessageSource");
	public static final Resource Meta = ResourceFactory.createResource(NS + "Meta");
	public static final Resource MetadataResource = ResourceFactory.createResource(NS + "MetadataResource");
	public static final Resource MolecularSequence = ResourceFactory.createResource(NS + "MolecularSequence");
	public static final Resource MolecularSequenceRelativeComponent = ResourceFactory
			.createResource(NS + "MolecularSequenceRelativeComponent");
	public static final Resource MolecularSequenceRelativeEditComponent = ResourceFactory
			.createResource(NS + "MolecularSequenceRelativeEditComponent");
	public static final Resource MolecularSequenceRelativeStartingSequenceComponent = ResourceFactory
			.createResource(NS + "MolecularSequenceRelativeStartingSequenceComponent");
	public static final Resource MonetaryComponent = ResourceFactory.createResource(NS + "MonetaryComponent");
	public static final Resource Money = ResourceFactory.createResource(NS + "Money");
	public static final Resource MoneyQuantity = ResourceFactory.createResource(NS + "MoneyQuantity");
	public static final Resource NamingSystem = ResourceFactory.createResource(NS + "NamingSystem");
	public static final Resource NamingSystemUniqueIdComponent = ResourceFactory
			.createResource(NS + "NamingSystemUniqueIdComponent");
	public static final Resource Narrative = ResourceFactory.createResource(NS + "Narrative");
	public static final Resource Narrative_div = ResourceFactory.createResource(NS + "Narrative.div");
	public static final Resource Note = ResourceFactory.createResource(NS + "Note");
	public static final Resource Notes = ResourceFactory.createResource(NS + "Notes");
	public static final Resource NutritionIntake = ResourceFactory.createResource(NS + "NutritionIntake");
	public static final Resource NutritionIntakeConsumedItemComponent = ResourceFactory
			.createResource(NS + "NutritionIntakeConsumedItemComponent");
	public static final Resource NutritionIntakeIngredientLabelComponent = ResourceFactory
			.createResource(NS + "NutritionIntakeIngredientLabelComponent");
	public static final Resource NutritionIntakePerformerComponent = ResourceFactory
			.createResource(NS + "NutritionIntakePerformerComponent");
	public static final Resource NutritionOrder = ResourceFactory.createResource(NS + "NutritionOrder");
	public static final Resource NutritionOrderEnteralFormulaAdditiveComponent = ResourceFactory
			.createResource(NS + "NutritionOrderEnteralFormulaAdditiveComponent");
	public static final Resource NutritionOrderEnteralFormulaAdministrationComponent = ResourceFactory
			.createResource(NS + "NutritionOrderEnteralFormulaAdministrationComponent");
	public static final Resource NutritionOrderEnteralFormulaComponent = ResourceFactory
			.createResource(NS + "NutritionOrderEnteralFormulaComponent");
	public static final Resource NutritionOrderOralDietComponent = ResourceFactory
			.createResource(NS + "NutritionOrderOralDietComponent");
	public static final Resource NutritionOrderOralDietNutrientComponent = ResourceFactory
			.createResource(NS + "NutritionOrderOralDietNutrientComponent");
	public static final Resource NutritionOrderOralDietTextureComponent = ResourceFactory
			.createResource(NS + "NutritionOrderOralDietTextureComponent");
	public static final Resource NutritionOrderSupplementComponent = ResourceFactory
			.createResource(NS + "NutritionOrderSupplementComponent");
	public static final Resource NutritionProduct = ResourceFactory.createResource(NS + "NutritionProduct");
	public static final Resource NutritionProductCharacteristicComponent = ResourceFactory
			.createResource(NS + "NutritionProductCharacteristicComponent");
	public static final Resource NutritionProductIngredientComponent = ResourceFactory
			.createResource(NS + "NutritionProductIngredientComponent");
	public static final Resource NutritionProductInstanceComponent = ResourceFactory
			.createResource(NS + "NutritionProductInstanceComponent");
	public static final Resource NutritionProductNutrientComponent = ResourceFactory
			.createResource(NS + "NutritionProductNutrientComponent");
	public static final Resource Observation = ResourceFactory.createResource(NS + "Observation");
	public static final Resource ObservationComponentComponent = ResourceFactory
			.createResource(NS + "ObservationComponentComponent");
	public static final Resource ObservationComponentComponentReferenceRange = ResourceFactory
			.createResource(NS + "ObservationComponentComponentReferenceRange");
	public static final Resource ObservationDefinition = ResourceFactory.createResource(NS + "ObservationDefinition");
	public static final Resource ObservationDefinitionComponentComponent = ResourceFactory
			.createResource(NS + "ObservationDefinitionComponentComponent");
	public static final Resource ObservationDefinitionComponentComponentQualifiedValue = ResourceFactory
			.createResource(NS + "ObservationDefinitionComponentComponentQualifiedValue");
	public static final Resource ObservationDefinitionQualifiedValueComponent = ResourceFactory
			.createResource(NS + "ObservationDefinitionQualifiedValueComponent");
	public static final Resource ObservationReferenceRangeComponent = ResourceFactory
			.createResource(NS + "ObservationReferenceRangeComponent");
	public static final Resource ObservationTriggeredByComponent = ResourceFactory
			.createResource(NS + "ObservationTriggeredByComponent");
	public static final Resource OperationDefinition = ResourceFactory.createResource(NS + "OperationDefinition");
	public static final Resource OperationDefinitionOverloadComponent = ResourceFactory
			.createResource(NS + "OperationDefinitionOverloadComponent");
	public static final Resource OperationDefinitionParameterBindingComponent = ResourceFactory
			.createResource(NS + "OperationDefinitionParameterBindingComponent");
	public static final Resource OperationDefinitionParameterComponent = ResourceFactory
			.createResource(NS + "OperationDefinitionParameterComponent");
	public static final Resource OperationDefinitionParameterComponentParameter = ResourceFactory
			.createResource(NS + "OperationDefinitionParameterComponentParameter");
	public static final Resource OperationDefinitionParameterComponentParameterParameter = ResourceFactory
			.createResource(NS + "OperationDefinitionParameterComponentParameterParameter");
	public static final Resource OperationDefinitionParameterReferencedFromComponent = ResourceFactory
			.createResource(NS + "OperationDefinitionParameterReferencedFromComponent");
	public static final Resource OperationOutcome = ResourceFactory.createResource(NS + "OperationOutcome");
	public static final Resource OperationOutcomeIssueComponent = ResourceFactory
			.createResource(NS + "OperationOutcomeIssueComponent");
	public static final Resource OralDietSchedule = ResourceFactory.createResource(NS + "OralDietSchedule");
	public static final Resource Organization = ResourceFactory.createResource(NS + "Organization");
	public static final Resource OrganizationAffiliation = ResourceFactory
			.createResource(NS + "OrganizationAffiliation");
	public static final Resource OrganizationQualificationComponent = ResourceFactory
			.createResource(NS + "OrganizationQualificationComponent");
	public static final Resource OtherElement = ResourceFactory.createResource(NS + "OtherElement");
	public static final Resource PackagedProductDefinition = ResourceFactory
			.createResource(NS + "PackagedProductDefinition");
	public static final Resource PackagedProductDefinitionLegalStatusOfSupplyComponent = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionLegalStatusOfSupplyComponent");
	public static final Resource PackagedProductDefinitionPackagingComponent = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionPackagingComponent");
	public static final Resource PackagedProductDefinitionPackagingComponentPackaging = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionPackagingComponentPackaging");
	public static final Resource PackagedProductDefinitionPackagingComponentPackagingPackaging = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionPackagingComponentPackagingPackaging");
	public static final Resource PackagedProductDefinitionPackagingContainedItemComponent = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionPackagingContainedItemComponent");
	public static final Resource PackagedProductDefinitionPackagingPropertyComponent = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionPackagingPropertyComponent");
	public static final Resource PackagedProductDefinitionProperty = ResourceFactory
			.createResource(NS + "PackagedProductDefinitionProperty");
	public static final Resource PackagingDistributor = ResourceFactory.createResource(NS + "PackagingDistributor");
	public static final Resource Parameter = ResourceFactory.createResource(NS + "Parameter");
	public static final Resource ParameterDefinition = ResourceFactory.createResource(NS + "ParameterDefinition");
	public static final Resource Parameters = ResourceFactory.createResource(NS + "Parameters");
	public static final Resource ParametersParameterComponent = ResourceFactory
			.createResource(NS + "ParametersParameterComponent");
	public static final Resource ParametersParameterComponentParameter = ResourceFactory
			.createResource(NS + "ParametersParameterComponentParameter");
	public static final Resource ParametersParameterComponentParameterParameter = ResourceFactory
			.createResource(NS + "ParametersParameterComponentParameterParameter");
	public static final Resource Patient = ResourceFactory.createResource(NS + "Patient");
	public static final Resource PatientCommunicationComponent = ResourceFactory
			.createResource(NS + "PatientCommunicationComponent");
	public static final Resource PatientLinkComponent = ResourceFactory.createResource(NS + "PatientLinkComponent");
	public static final Resource Payee = ResourceFactory.createResource(NS + "Payee");
	public static final Resource Payment = ResourceFactory.createResource(NS + "Payment");
	public static final Resource PaymentNotice = ResourceFactory.createResource(NS + "PaymentNotice");
	public static final Resource PaymentReconciliation = ResourceFactory.createResource(NS + "PaymentReconciliation");
	public static final Resource PaymentReconciliationAllocationComponent = ResourceFactory
			.createResource(NS + "PaymentReconciliationAllocationComponent");
	public static final Resource Period = ResourceFactory.createResource(NS + "Period");
	public static final Resource Permission = ResourceFactory.createResource(NS + "Permission");
	public static final Resource PermissionJustificationComponent = ResourceFactory
			.createResource(NS + "PermissionJustificationComponent");
	public static final Resource PermissionRuleActivityComponent = ResourceFactory
			.createResource(NS + "PermissionRuleActivityComponent");
	public static final Resource PermissionRuleDataComponent = ResourceFactory
			.createResource(NS + "PermissionRuleDataComponent");
	public static final Resource PermissionRuleDataResourceComponent = ResourceFactory
			.createResource(NS + "PermissionRuleDataResourceComponent");
	public static final Resource Person = ResourceFactory.createResource(NS + "Person");
	public static final Resource PersonCommunicationComponent = ResourceFactory
			.createResource(NS + "PersonCommunicationComponent");
	public static final Resource PersonLinkComponent = ResourceFactory.createResource(NS + "PersonLinkComponent");
	public static final Resource PlanBenefit = ResourceFactory.createResource(NS + "PlanBenefit");
	public static final Resource PlanDefinition = ResourceFactory.createResource(NS + "PlanDefinition");
	public static final Resource PlanDefinitionActionComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionComponent");
	public static final Resource PlanDefinitionActionComponentAction = ResourceFactory
			.createResource(NS + "PlanDefinitionActionComponentAction");
	public static final Resource PlanDefinitionActionComponentActionAction = ResourceFactory
			.createResource(NS + "PlanDefinitionActionComponentActionAction");
	public static final Resource PlanDefinitionActionConditionComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionConditionComponent");
	public static final Resource PlanDefinitionActionDynamicValueComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionDynamicValueComponent");
	public static final Resource PlanDefinitionActionInputComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionInputComponent");
	public static final Resource PlanDefinitionActionOutputComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionOutputComponent");
	public static final Resource PlanDefinitionActionParticipantComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionParticipantComponent");
	public static final Resource PlanDefinitionActionRelatedActionComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActionRelatedActionComponent");
	public static final Resource PlanDefinitionActorComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActorComponent");
	public static final Resource PlanDefinitionActorOptionComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionActorOptionComponent");
	public static final Resource PlanDefinitionGoalComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionGoalComponent");
	public static final Resource PlanDefinitionGoalTargetComponent = ResourceFactory
			.createResource(NS + "PlanDefinitionGoalTargetComponent");
	public static final Resource Practitioner = ResourceFactory.createResource(NS + "Practitioner");
	public static final Resource PractitionerCommunicationComponent = ResourceFactory
			.createResource(NS + "PractitionerCommunicationComponent");
	public static final Resource PractitionerQualificationComponent = ResourceFactory
			.createResource(NS + "PractitionerQualificationComponent");
	public static final Resource PractitionerRole = ResourceFactory.createResource(NS + "PractitionerRole");
	public static final Resource Primitive = ResourceFactory.createResource(NS + "Primitive");
	public static final Resource PrimitiveType = ResourceFactory.createResource(NS + "PrimitiveType");
	public static final Resource Prism = ResourceFactory.createResource(NS + "Prism");
	public static final Resource Procedure = ResourceFactory.createResource(NS + "Procedure");
	public static final Resource ProcedureFocalDeviceComponent = ResourceFactory
			.createResource(NS + "ProcedureFocalDeviceComponent");
	public static final Resource ProcedurePerformerComponent = ResourceFactory
			.createResource(NS + "ProcedurePerformerComponent");
	public static final Resource ProductShelfLife = ResourceFactory.createResource(NS + "ProductShelfLife");
	public static final Resource Property = ResourceFactory.createResource(NS + "Property");
	public static final Resource Provenance = ResourceFactory.createResource(NS + "Provenance");
	public static final Resource ProvenanceAgentComponent = ResourceFactory
			.createResource(NS + "ProvenanceAgentComponent");
	public static final Resource ProvenanceEntityComponent = ResourceFactory
			.createResource(NS + "ProvenanceEntityComponent");
	public static final Resource ProvenanceEntityComponentAgent = ResourceFactory
			.createResource(NS + "ProvenanceEntityComponentAgent");
	public static final Resource Quantity = ResourceFactory.createResource(NS + "Quantity");
	public static final Resource Questionnaire = ResourceFactory.createResource(NS + "Questionnaire");
	public static final Resource QuestionnaireItemAnswerOptionComponent = ResourceFactory
			.createResource(NS + "QuestionnaireItemAnswerOptionComponent");
	public static final Resource QuestionnaireItemComponent = ResourceFactory
			.createResource(NS + "QuestionnaireItemComponent");
	public static final Resource QuestionnaireItemComponentItem = ResourceFactory
			.createResource(NS + "QuestionnaireItemComponentItem");
	public static final Resource QuestionnaireItemComponentItemItem = ResourceFactory
			.createResource(NS + "QuestionnaireItemComponentItemItem");
	public static final Resource QuestionnaireItemEnableWhenComponent = ResourceFactory
			.createResource(NS + "QuestionnaireItemEnableWhenComponent");
	public static final Resource QuestionnaireItemInitialComponent = ResourceFactory
			.createResource(NS + "QuestionnaireItemInitialComponent");
	public static final Resource QuestionnaireResponse = ResourceFactory.createResource(NS + "QuestionnaireResponse");
	public static final Resource QuestionnaireResponseItemAnswerComponent = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemAnswerComponent");
	public static final Resource QuestionnaireResponseItemComponent = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemComponent");
	public static final Resource QuestionnaireResponseItemComponent_QuestionnaireResponseItemAnswerComponentItem = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemComponent.QuestionnaireResponseItemAnswerComponentItem");
	public static final Resource QuestionnaireResponseItemComponent_QuestionnaireResponseItemAnswerComponentItem_QuestionnaireResponseItemAnswerComponentItem = ResourceFactory
			.createResource(NS
					+ "QuestionnaireResponseItemComponent.QuestionnaireResponseItemAnswerComponentItem.QuestionnaireResponseItemAnswerComponentItem");
	public static final Resource QuestionnaireResponseItemComponent_QuestionnaireResponseItemAnswerComponentItemItem = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemComponent.QuestionnaireResponseItemAnswerComponentItemItem");
	public static final Resource QuestionnaireResponseItemComponentItem = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemComponentItem");
	public static final Resource QuestionnaireResponseItemComponentItem_QuestionnaireResponseItemAnswerComponentItem = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemComponentItem.QuestionnaireResponseItemAnswerComponentItem");
	public static final Resource QuestionnaireResponseItemComponentItemItem = ResourceFactory
			.createResource(NS + "QuestionnaireResponseItemComponentItemItem");
	public static final Resource Range = ResourceFactory.createResource(NS + "Range");
	public static final Resource Ratio = ResourceFactory.createResource(NS + "Ratio");
	public static final Resource RatioRange = ResourceFactory.createResource(NS + "RatioRange");
	public static final Resource Reason = ResourceFactory.createResource(NS + "Reason");
	public static final Resource Reference = ResourceFactory.createResource(NS + "Reference");
	public static final Resource RegulatedAuthorization = ResourceFactory.createResource(NS + "RegulatedAuthorization");
	public static final Resource RegulatedAuthorizationCaseComponent = ResourceFactory
			.createResource(NS + "RegulatedAuthorizationCaseComponent");
	public static final Resource RegulatedAuthorizationCaseComponentCase = ResourceFactory
			.createResource(NS + "RegulatedAuthorizationCaseComponentCase");
	public static final Resource RegulatedAuthorizationCaseComponentCaseCase = ResourceFactory
			.createResource(NS + "RegulatedAuthorizationCaseComponentCaseCase");
	public static final Resource RelatedArtifact = ResourceFactory.createResource(NS + "RelatedArtifact");
	public static final Resource RelatedClaim = ResourceFactory.createResource(NS + "RelatedClaim");
	public static final Resource RelatedPerson = ResourceFactory.createResource(NS + "RelatedPerson");
	public static final Resource RelatedPersonCommunicationComponent = ResourceFactory
			.createResource(NS + "RelatedPersonCommunicationComponent");
	public static final Resource RequestOrchestration = ResourceFactory.createResource(NS + "RequestOrchestration");
	public static final Resource RequestOrchestrationActionComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionComponent");
	public static final Resource RequestOrchestrationActionComponentAction = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionComponentAction");
	public static final Resource RequestOrchestrationActionComponentActionAction = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionComponentActionAction");
	public static final Resource RequestOrchestrationActionConditionComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionConditionComponent");
	public static final Resource RequestOrchestrationActionDynamicValueComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionDynamicValueComponent");
	public static final Resource RequestOrchestrationActionInputComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionInputComponent");
	public static final Resource RequestOrchestrationActionOutputComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionOutputComponent");
	public static final Resource RequestOrchestrationActionParticipantComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionParticipantComponent");
	public static final Resource RequestOrchestrationActionRelatedActionComponent = ResourceFactory
			.createResource(NS + "RequestOrchestrationActionRelatedActionComponent");
	public static final Resource Requirements = ResourceFactory.createResource(NS + "Requirements");
	public static final Resource RequirementsStatementComponent = ResourceFactory
			.createResource(NS + "RequirementsStatementComponent");
	public static final Resource ResearchStudy = ResourceFactory.createResource(NS + "ResearchStudy");
	public static final Resource ResearchStudyAssociatedPartyComponent = ResourceFactory
			.createResource(NS + "ResearchStudyAssociatedPartyComponent");
	public static final Resource ResearchStudyComparisonGroupComponent = ResourceFactory
			.createResource(NS + "ResearchStudyComparisonGroupComponent");
	public static final Resource ResearchStudyLabelComponent = ResourceFactory
			.createResource(NS + "ResearchStudyLabelComponent");
	public static final Resource ResearchStudyObjectiveComponent = ResourceFactory
			.createResource(NS + "ResearchStudyObjectiveComponent");
	public static final Resource ResearchStudyOutcomeMeasureComponent = ResourceFactory
			.createResource(NS + "ResearchStudyOutcomeMeasureComponent");
	public static final Resource ResearchStudyProgressStatusComponent = ResourceFactory
			.createResource(NS + "ResearchStudyProgressStatusComponent");
	public static final Resource ResearchStudyRecruitmentComponent = ResourceFactory
			.createResource(NS + "ResearchStudyRecruitmentComponent");
	public static final Resource ResearchSubject = ResourceFactory.createResource(NS + "ResearchSubject");
	public static final Resource ResearchSubjectProgressComponent = ResourceFactory
			.createResource(NS + "ResearchSubjectProgressComponent");
	public static final Resource fhir_Resource = ResourceFactory.createResource(NS + "Resource");
	public static final Resource ResourceInteraction = ResourceFactory.createResource(NS + "ResourceInteraction");
	public static final Resource ReviewOutcome = ResourceFactory.createResource(NS + "ReviewOutcome");
	public static final Resource RiskAssessment = ResourceFactory.createResource(NS + "RiskAssessment");
	public static final Resource RiskAssessmentPredictionComponent = ResourceFactory
			.createResource(NS + "RiskAssessmentPredictionComponent");
	public static final Resource Rule = ResourceFactory.createResource(NS + "Rule");
	public static final Resource SampledData = ResourceFactory.createResource(NS + "SampledData");
	public static final Resource Schedule = ResourceFactory.createResource(NS + "Schedule");
	public static final Resource SearchParameter = ResourceFactory.createResource(NS + "SearchParameter");
	public static final Resource SearchParameterComponentComponent = ResourceFactory
			.createResource(NS + "SearchParameterComponentComponent");
	public static final Resource Section = ResourceFactory.createResource(NS + "Section");
	public static final Resource SecurityLabel = ResourceFactory.createResource(NS + "SecurityLabel");
	public static final Resource ServiceRequest = ResourceFactory.createResource(NS + "ServiceRequest");
	public static final Resource ServiceRequestOrderDetailComponent = ResourceFactory
			.createResource(NS + "ServiceRequestOrderDetailComponent");
	public static final Resource ServiceRequestOrderDetailParameterComponent = ResourceFactory
			.createResource(NS + "ServiceRequestOrderDetailParameterComponent");
	public static final Resource ServiceRequestPatientInstructionComponent = ResourceFactory
			.createResource(NS + "ServiceRequestPatientInstructionComponent");
	public static final Resource SetupAction = ResourceFactory.createResource(NS + "SetupAction");
	public static final Resource Signatory = ResourceFactory.createResource(NS + "Signatory");
	public static final Resource Signature = ResourceFactory.createResource(NS + "Signature");
	public static final Resource SimpleQuantity = ResourceFactory.createResource(NS + "SimpleQuantity");
	public static final Resource Slot = ResourceFactory.createResource(NS + "Slot");
	public static final Resource SourceElement = ResourceFactory.createResource(NS + "SourceElement");
	public static final Resource Specimen = ResourceFactory.createResource(NS + "Specimen");
	public static final Resource SpecimenCollectionComponent = ResourceFactory
			.createResource(NS + "SpecimenCollectionComponent");
	public static final Resource SpecimenContainerComponent = ResourceFactory
			.createResource(NS + "SpecimenContainerComponent");
	public static final Resource SpecimenDefinition = ResourceFactory.createResource(NS + "SpecimenDefinition");
	public static final Resource SpecimenDefinitionTypeTestedComponent = ResourceFactory
			.createResource(NS + "SpecimenDefinitionTypeTestedComponent");
	public static final Resource SpecimenDefinitionTypeTestedContainerAdditiveComponent = ResourceFactory
			.createResource(NS + "SpecimenDefinitionTypeTestedContainerAdditiveComponent");
	public static final Resource SpecimenDefinitionTypeTestedContainerComponent = ResourceFactory
			.createResource(NS + "SpecimenDefinitionTypeTestedContainerComponent");
	public static final Resource SpecimenDefinitionTypeTestedHandlingComponent = ResourceFactory
			.createResource(NS + "SpecimenDefinitionTypeTestedHandlingComponent");
	public static final Resource SpecimenFeatureComponent = ResourceFactory
			.createResource(NS + "SpecimenFeatureComponent");
	public static final Resource SpecimenProcessingComponent = ResourceFactory
			.createResource(NS + "SpecimenProcessingComponent");
	public static final Resource StratifierGroup = ResourceFactory.createResource(NS + "StratifierGroup");
	public static final Resource StratifierGroupPopulation = ResourceFactory
			.createResource(NS + "StratifierGroupPopulation");
	public static final Resource StructureDefinition = ResourceFactory.createResource(NS + "StructureDefinition");
	public static final Resource StructureDefinitionContextComponent = ResourceFactory
			.createResource(NS + "StructureDefinitionContextComponent");
	public static final Resource StructureDefinitionDifferentialComponent = ResourceFactory
			.createResource(NS + "StructureDefinitionDifferentialComponent");
	public static final Resource StructureDefinitionMappingComponent = ResourceFactory
			.createResource(NS + "StructureDefinitionMappingComponent");
	public static final Resource StructureDefinitionSnapshotComponent = ResourceFactory
			.createResource(NS + "StructureDefinitionSnapshotComponent");
	public static final Resource StructureMap = ResourceFactory.createResource(NS + "StructureMap");
	public static final Resource StructureMapConstComponent = ResourceFactory
			.createResource(NS + "StructureMapConstComponent");
	public static final Resource StructureMapGroupComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupComponent");
	public static final Resource StructureMapGroupComponent_StructureMapGroupRuleComponent_StructureMapGroupRuleDependentComponentParameter = ResourceFactory
			.createResource(NS
					+ "StructureMapGroupComponent.StructureMapGroupRuleComponent.StructureMapGroupRuleDependentComponentParameter");
	public static final Resource StructureMapGroupComponent_StructureMapGroupRuleComponentRule = ResourceFactory
			.createResource(NS + "StructureMapGroupComponent.StructureMapGroupRuleComponentRule");
	public static final Resource StructureMapGroupComponent_StructureMapGroupRuleComponentRule_StructureMapGroupRuleDependentComponentParameter = ResourceFactory
			.createResource(NS
					+ "StructureMapGroupComponent.StructureMapGroupRuleComponentRule.StructureMapGroupRuleDependentComponentParameter");
	public static final Resource StructureMapGroupComponent_StructureMapGroupRuleComponentRuleRule = ResourceFactory
			.createResource(NS + "StructureMapGroupComponent.StructureMapGroupRuleComponentRuleRule");
	public static final Resource StructureMapGroupInputComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupInputComponent");
	public static final Resource StructureMapGroupRuleComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupRuleComponent");
	public static final Resource StructureMapGroupRuleDependentComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupRuleDependentComponent");
	public static final Resource StructureMapGroupRuleSourceComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupRuleSourceComponent");
	public static final Resource StructureMapGroupRuleTargetComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupRuleTargetComponent");
	public static final Resource StructureMapGroupRuleTargetParameterComponent = ResourceFactory
			.createResource(NS + "StructureMapGroupRuleTargetParameterComponent");
	public static final Resource StructureMapStructureComponent = ResourceFactory
			.createResource(NS + "StructureMapStructureComponent");
	public static final Resource SubDetail = ResourceFactory.createResource(NS + "SubDetail");
	public static final Resource Subscription = ResourceFactory.createResource(NS + "Subscription");
	public static final Resource SubscriptionFilterByComponent = ResourceFactory
			.createResource(NS + "SubscriptionFilterByComponent");
	public static final Resource SubscriptionParameterComponent = ResourceFactory
			.createResource(NS + "SubscriptionParameterComponent");
	public static final Resource SubscriptionStatus = ResourceFactory.createResource(NS + "SubscriptionStatus");
	public static final Resource SubscriptionStatusNotificationEventComponent = ResourceFactory
			.createResource(NS + "SubscriptionStatusNotificationEventComponent");
	public static final Resource SubscriptionTopic = ResourceFactory.createResource(NS + "SubscriptionTopic");
	public static final Resource SubscriptionTopicCanFilterByComponent = ResourceFactory
			.createResource(NS + "SubscriptionTopicCanFilterByComponent");
	public static final Resource SubscriptionTopicEventTriggerComponent = ResourceFactory
			.createResource(NS + "SubscriptionTopicEventTriggerComponent");
	public static final Resource SubscriptionTopicNotificationShapeComponent = ResourceFactory
			.createResource(NS + "SubscriptionTopicNotificationShapeComponent");
	public static final Resource SubscriptionTopicResourceTriggerComponent = ResourceFactory
			.createResource(NS + "SubscriptionTopicResourceTriggerComponent");
	public static final Resource SubscriptionTopicResourceTriggerQueryCriteriaComponent = ResourceFactory
			.createResource(NS + "SubscriptionTopicResourceTriggerQueryCriteriaComponent");
	public static final Resource Substance = ResourceFactory.createResource(NS + "Substance");
	public static final Resource SubstanceDefinition = ResourceFactory.createResource(NS + "SubstanceDefinition");
	public static final Resource SubstanceDefinitionCharacterizationComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionCharacterizationComponent");
	public static final Resource SubstanceDefinitionCodeComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionCodeComponent");
	public static final Resource SubstanceDefinitionMoietyComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionMoietyComponent");
	public static final Resource SubstanceDefinitionMolecularWeightComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionMolecularWeightComponent");
	public static final Resource SubstanceDefinitionNameComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionNameComponent");
	public static final Resource SubstanceDefinitionNameComponentName = ResourceFactory
			.createResource(NS + "SubstanceDefinitionNameComponentName");
	public static final Resource SubstanceDefinitionNameComponentNameName = ResourceFactory
			.createResource(NS + "SubstanceDefinitionNameComponentNameName");
	public static final Resource SubstanceDefinitionNameOfficialComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionNameOfficialComponent");
	public static final Resource SubstanceDefinitionPropertyComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionPropertyComponent");
	public static final Resource SubstanceDefinitionRelationshipComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionRelationshipComponent");
	public static final Resource SubstanceDefinitionSourceMaterialComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionSourceMaterialComponent");
	public static final Resource SubstanceDefinitionStructureComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionStructureComponent");
	public static final Resource SubstanceDefinitionStructureComponentMolecularWeight = ResourceFactory
			.createResource(NS + "SubstanceDefinitionStructureComponentMolecularWeight");
	public static final Resource SubstanceDefinitionStructureRepresentationComponent = ResourceFactory
			.createResource(NS + "SubstanceDefinitionStructureRepresentationComponent");
	public static final Resource SubstanceIngredientComponent = ResourceFactory
			.createResource(NS + "SubstanceIngredientComponent");
	public static final Resource SubstanceNucleicAcid = ResourceFactory.createResource(NS + "SubstanceNucleicAcid");
	public static final Resource SubstanceNucleicAcidSubunitComponent = ResourceFactory
			.createResource(NS + "SubstanceNucleicAcidSubunitComponent");
	public static final Resource SubstanceNucleicAcidSubunitLinkageComponent = ResourceFactory
			.createResource(NS + "SubstanceNucleicAcidSubunitLinkageComponent");
	public static final Resource SubstanceNucleicAcidSubunitSugarComponent = ResourceFactory
			.createResource(NS + "SubstanceNucleicAcidSubunitSugarComponent");
	public static final Resource SubstancePolymer = ResourceFactory.createResource(NS + "SubstancePolymer");
	public static final Resource SubstancePolymerMonomerSetComponent = ResourceFactory
			.createResource(NS + "SubstancePolymerMonomerSetComponent");
	public static final Resource SubstancePolymerMonomerSetStartingMaterialComponent = ResourceFactory
			.createResource(NS + "SubstancePolymerMonomerSetStartingMaterialComponent");
	public static final Resource SubstancePolymerRepeatComponent = ResourceFactory
			.createResource(NS + "SubstancePolymerRepeatComponent");
	public static final Resource SubstancePolymerRepeatRepeatUnitComponent = ResourceFactory
			.createResource(NS + "SubstancePolymerRepeatRepeatUnitComponent");
	public static final Resource SubstancePolymerRepeatRepeatUnitDegreeOfPolymerisationComponent = ResourceFactory
			.createResource(NS + "SubstancePolymerRepeatRepeatUnitDegreeOfPolymerisationComponent");
	public static final Resource SubstancePolymerRepeatRepeatUnitStructuralRepresentationComponent = ResourceFactory
			.createResource(NS + "SubstancePolymerRepeatRepeatUnitStructuralRepresentationComponent");
	public static final Resource SubstanceProtein = ResourceFactory.createResource(NS + "SubstanceProtein");
	public static final Resource SubstanceProteinSubunitComponent = ResourceFactory
			.createResource(NS + "SubstanceProteinSubunitComponent");
	public static final Resource SubstanceReferenceInformation = ResourceFactory
			.createResource(NS + "SubstanceReferenceInformation");
	public static final Resource SubstanceReferenceInformationGeneComponent = ResourceFactory
			.createResource(NS + "SubstanceReferenceInformationGeneComponent");
	public static final Resource SubstanceReferenceInformationGeneElementComponent = ResourceFactory
			.createResource(NS + "SubstanceReferenceInformationGeneElementComponent");
	public static final Resource SubstanceReferenceInformationTargetComponent = ResourceFactory
			.createResource(NS + "SubstanceReferenceInformationTargetComponent");
	public static final Resource SubstanceSourceMaterial = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterial");
	public static final Resource SubstanceSourceMaterialFractionDescriptionComponent = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterialFractionDescriptionComponent");
	public static final Resource SubstanceSourceMaterialOrganismAuthorComponent = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterialOrganismAuthorComponent");
	public static final Resource SubstanceSourceMaterialOrganismComponent = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterialOrganismComponent");
	public static final Resource SubstanceSourceMaterialOrganismHybridComponent = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterialOrganismHybridComponent");
	public static final Resource SubstanceSourceMaterialOrganismOrganismGeneralComponent = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterialOrganismOrganismGeneralComponent");
	public static final Resource SubstanceSourceMaterialPartDescriptionComponent = ResourceFactory
			.createResource(NS + "SubstanceSourceMaterialPartDescriptionComponent");
	public static final Resource SupplementSchedule = ResourceFactory.createResource(NS + "SupplementSchedule");
	public static final Resource SupplyDelivery = ResourceFactory.createResource(NS + "SupplyDelivery");
	public static final Resource SupplyDeliverySuppliedItemComponent = ResourceFactory
			.createResource(NS + "SupplyDeliverySuppliedItemComponent");
	public static final Resource SupplyRequest = ResourceFactory.createResource(NS + "SupplyRequest");
	public static final Resource SupplyRequestParameterComponent = ResourceFactory
			.createResource(NS + "SupplyRequestParameterComponent");
	public static final Resource SupportingInformation = ResourceFactory.createResource(NS + "SupportingInformation");
	public static final Resource SystemInteraction = ResourceFactory.createResource(NS + "SystemInteraction");
	public static final Resource TargetElement = ResourceFactory.createResource(NS + "TargetElement");
	public static final Resource Task = ResourceFactory.createResource(NS + "Task");
	public static final Resource TaskInputComponent = ResourceFactory.createResource(NS + "TaskInputComponent");
	public static final Resource TaskOutputComponent = ResourceFactory.createResource(NS + "TaskOutputComponent");
	public static final Resource TaskPerformerComponent = ResourceFactory.createResource(NS + "TaskPerformerComponent");
	public static final Resource TaskRestrictionComponent = ResourceFactory
			.createResource(NS + "TaskRestrictionComponent");
	public static final Resource TeardownAction = ResourceFactory.createResource(NS + "TeardownAction");
	public static final Resource Term = ResourceFactory.createResource(NS + "Term");
	public static final Resource TerminologyCapabilities = ResourceFactory
			.createResource(NS + "TerminologyCapabilities");
	public static final Resource TerminologyCapabilitiesClosureComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesClosureComponent");
	public static final Resource TerminologyCapabilitiesCodeSystemComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesCodeSystemComponent");
	public static final Resource TerminologyCapabilitiesCodeSystemVersionComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesCodeSystemVersionComponent");
	public static final Resource TerminologyCapabilitiesCodeSystemVersionFilterComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesCodeSystemVersionFilterComponent");
	public static final Resource TerminologyCapabilitiesExpansionComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesExpansionComponent");
	public static final Resource TerminologyCapabilitiesExpansionParameterComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesExpansionParameterComponent");
	public static final Resource TerminologyCapabilitiesImplementationComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesImplementationComponent");
	public static final Resource TerminologyCapabilitiesSoftwareComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesSoftwareComponent");
	public static final Resource TerminologyCapabilitiesTranslationComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesTranslationComponent");
	public static final Resource TerminologyCapabilitiesValidateCodeComponent = ResourceFactory
			.createResource(NS + "TerminologyCapabilitiesValidateCodeComponent");
	public static final Resource TestAction = ResourceFactory.createResource(NS + "TestAction");
	public static final Resource TestCaseDependency = ResourceFactory.createResource(NS + "TestCaseDependency");
	public static final Resource TestPlan = ResourceFactory.createResource(NS + "TestPlan");
	public static final Resource TestPlanDependencyComponent = ResourceFactory
			.createResource(NS + "TestPlanDependencyComponent");
	public static final Resource TestPlanTestCaseAssertionComponent = ResourceFactory
			.createResource(NS + "TestPlanTestCaseAssertionComponent");
	public static final Resource TestPlanTestCaseComponent = ResourceFactory
			.createResource(NS + "TestPlanTestCaseComponent");
	public static final Resource TestPlanTestCaseTestDataComponent = ResourceFactory
			.createResource(NS + "TestPlanTestCaseTestDataComponent");
	public static final Resource TestPlanTestCaseTestRunComponent = ResourceFactory
			.createResource(NS + "TestPlanTestCaseTestRunComponent");
	public static final Resource TestPlanTestCaseTestRunScriptComponent = ResourceFactory
			.createResource(NS + "TestPlanTestCaseTestRunScriptComponent");
	public static final Resource TestReport = ResourceFactory.createResource(NS + "TestReport");
	public static final Resource TestReportParticipantComponent = ResourceFactory
			.createResource(NS + "TestReportParticipantComponent");
	public static final Resource TestReportSetupActionAssertComponent = ResourceFactory
			.createResource(NS + "TestReportSetupActionAssertComponent");
	public static final Resource TestReportSetupActionAssertRequirementComponent = ResourceFactory
			.createResource(NS + "TestReportSetupActionAssertRequirementComponent");
	public static final Resource TestReportSetupActionOperationComponent = ResourceFactory
			.createResource(NS + "TestReportSetupActionOperationComponent");
	public static final Resource TestReportSetupComponent = ResourceFactory
			.createResource(NS + "TestReportSetupComponent");
	public static final Resource TestReportTeardownComponent = ResourceFactory
			.createResource(NS + "TestReportTeardownComponent");
	public static final Resource TestReportTeardownComponent_TeardownActionOperation = ResourceFactory
			.createResource(NS + "TestReportTeardownComponent.TeardownActionOperation");
	public static final Resource TestReportTestComponent = ResourceFactory
			.createResource(NS + "TestReportTestComponent");
	public static final Resource TestReportTestComponent_TestActionAssert = ResourceFactory
			.createResource(NS + "TestReportTestComponent.TestActionAssert");
	public static final Resource TestReportTestComponent_TestActionOperation = ResourceFactory
			.createResource(NS + "TestReportTestComponent.TestActionOperation");
	public static final Resource TestScript = ResourceFactory.createResource(NS + "TestScript");
	public static final Resource TestScriptDestinationComponent = ResourceFactory
			.createResource(NS + "TestScriptDestinationComponent");
	public static final Resource TestScriptFixtureComponent = ResourceFactory
			.createResource(NS + "TestScriptFixtureComponent");
	public static final Resource TestScriptMetadataCapabilityComponent = ResourceFactory
			.createResource(NS + "TestScriptMetadataCapabilityComponent");
	public static final Resource TestScriptMetadataComponent = ResourceFactory
			.createResource(NS + "TestScriptMetadataComponent");
	public static final Resource TestScriptMetadataLinkComponent = ResourceFactory
			.createResource(NS + "TestScriptMetadataLinkComponent");
	public static final Resource TestScriptOriginComponent = ResourceFactory
			.createResource(NS + "TestScriptOriginComponent");
	public static final Resource TestScriptScopeComponent = ResourceFactory
			.createResource(NS + "TestScriptScopeComponent");
	public static final Resource TestScriptSetupActionAssertComponent = ResourceFactory
			.createResource(NS + "TestScriptSetupActionAssertComponent");
	public static final Resource TestScriptSetupActionAssertRequirementComponent = ResourceFactory
			.createResource(NS + "TestScriptSetupActionAssertRequirementComponent");
	public static final Resource TestScriptSetupActionOperationComponent = ResourceFactory
			.createResource(NS + "TestScriptSetupActionOperationComponent");
	public static final Resource TestScriptSetupActionOperationRequestHeaderComponent = ResourceFactory
			.createResource(NS + "TestScriptSetupActionOperationRequestHeaderComponent");
	public static final Resource TestScriptSetupComponent = ResourceFactory
			.createResource(NS + "TestScriptSetupComponent");
	public static final Resource TestScriptTeardownComponent = ResourceFactory
			.createResource(NS + "TestScriptTeardownComponent");
	public static final Resource TestScriptTeardownComponent_TeardownActionOperation = ResourceFactory
			.createResource(NS + "TestScriptTeardownComponent.TeardownActionOperation");
	public static final Resource TestScriptTestComponent = ResourceFactory
			.createResource(NS + "TestScriptTestComponent");
	public static final Resource TestScriptTestComponent_TestActionAssert = ResourceFactory
			.createResource(NS + "TestScriptTestComponent.TestActionAssert");
	public static final Resource TestScriptTestComponent_TestActionOperation = ResourceFactory
			.createResource(NS + "TestScriptTestComponent.TestActionOperation");
	public static final Resource TestScriptVariableComponent = ResourceFactory
			.createResource(NS + "TestScriptVariableComponent");
	public static final Resource Timing = ResourceFactory.createResource(NS + "Timing");
	public static final Resource TimingRepeatComponent = ResourceFactory.createResource(NS + "TimingRepeatComponent");
	public static final Resource Total = ResourceFactory.createResource(NS + "Total");
	public static final Resource Transport = ResourceFactory.createResource(NS + "Transport");
	public static final Resource TransportOutputComponent = ResourceFactory
			.createResource(NS + "TransportOutputComponent");
	public static final Resource TransportRestrictionComponent = ResourceFactory
			.createResource(NS + "TransportRestrictionComponent");
	public static final Resource TriggerDefinition = ResourceFactory.createResource(NS + "TriggerDefinition");
	public static final Resource TypeRefComponent = ResourceFactory.createResource(NS + "TypeRefComponent");
	public static final Resource UdiDeviceIdentifierMarketDistribution = ResourceFactory
			.createResource(NS + "UdiDeviceIdentifierMarketDistribution");
	public static final Resource UsageContext = ResourceFactory.createResource(NS + "UsageContext");
	public static final Resource ValueSet = ResourceFactory.createResource(NS + "ValueSet");
	public static final Resource ValueSetComposeComponent = ResourceFactory
			.createResource(NS + "ValueSetComposeComponent");
	public static final Resource ValueSetComposeComponentInclude = ResourceFactory
			.createResource(NS + "ValueSetComposeComponentInclude");
	public static final Resource ValueSetComposeIncludeConceptDesignationComponent = ResourceFactory
			.createResource(NS + "ValueSetComposeIncludeConceptDesignationComponent");
	public static final Resource ValueSetComposeIncludeFilterComponent = ResourceFactory
			.createResource(NS + "ValueSetComposeIncludeFilterComponent");
	public static final Resource ValueSetExpansionComponent = ResourceFactory
			.createResource(NS + "ValueSetExpansionComponent");
	public static final Resource ValueSetExpansionComponent_ValueSetExpansionContainsComponentContains = ResourceFactory
			.createResource(NS + "ValueSetExpansionComponent.ValueSetExpansionContainsComponentContains");
	public static final Resource ValueSetExpansionComponent_ValueSetExpansionContainsComponentContainsContains = ResourceFactory
			.createResource(NS + "ValueSetExpansionComponent.ValueSetExpansionContainsComponentContainsContains");
	public static final Resource ValueSetExpansionComponent_ValueSetExpansionContainsComponentContainsDesignation = ResourceFactory
			.createResource(NS + "ValueSetExpansionComponent.ValueSetExpansionContainsComponentContainsDesignation");
	public static final Resource ValueSetExpansionComponent_ValueSetExpansionContainsComponentDesignation = ResourceFactory
			.createResource(NS + "ValueSetExpansionComponent.ValueSetExpansionContainsComponentDesignation");
	public static final Resource ValueSetExpansionContainsComponent = ResourceFactory
			.createResource(NS + "ValueSetExpansionContainsComponent");
	public static final Resource ValueSetExpansionParameterComponent = ResourceFactory
			.createResource(NS + "ValueSetExpansionParameterComponent");
	public static final Resource ValueSetExpansionPropertyComponent = ResourceFactory
			.createResource(NS + "ValueSetExpansionPropertyComponent");
	public static final Resource ValueSetScopeComponent = ResourceFactory.createResource(NS + "ValueSetScopeComponent");
	public static final Resource ValuedItem = ResourceFactory.createResource(NS + "ValuedItem");
	public static final Resource VerificationResult = ResourceFactory.createResource(NS + "VerificationResult");
	public static final Resource VerificationResultAttestationComponent = ResourceFactory
			.createResource(NS + "VerificationResultAttestationComponent");
	public static final Resource VerificationResultPrimarySourceComponent = ResourceFactory
			.createResource(NS + "VerificationResultPrimarySourceComponent");
	public static final Resource VerificationResultValidatorComponent = ResourceFactory
			.createResource(NS + "VerificationResultValidatorComponent");
	public static final Resource VirtualServiceDetail = ResourceFactory.createResource(NS + "VirtualServiceDetail");
	public static final Resource VisionPrescription = ResourceFactory.createResource(NS + "VisionPrescription");
	public static final Resource VisionPrescriptionLensSpecificationComponent = ResourceFactory
			.createResource(NS + "VisionPrescriptionLensSpecificationComponent");
	public static final Resource _Account = ResourceFactory.createResource(NS + "_Account");
	public static final Resource _AdministrableProductDefinition = ResourceFactory
			.createResource(NS + "_AdministrableProductDefinition");
	public static final Resource _AdverseEvent = ResourceFactory.createResource(NS + "_AdverseEvent");
	public static final Resource _AllergyIntolerance = ResourceFactory.createResource(NS + "_AllergyIntolerance");
	public static final Resource _Appointment = ResourceFactory.createResource(NS + "_Appointment");
	public static final Resource _AppointmentResponse = ResourceFactory.createResource(NS + "_AppointmentResponse");
	public static final Resource _ArtifactAssessment = ResourceFactory.createResource(NS + "_ArtifactAssessment");
	public static final Resource _AuditEvent = ResourceFactory.createResource(NS + "_AuditEvent");
	public static final Resource _BackboneElement = ResourceFactory.createResource(NS + "_BackboneElement");
	public static final Resource _BackboneType = ResourceFactory.createResource(NS + "_BackboneType");
	public static final Resource _Basic = ResourceFactory.createResource(NS + "_Basic");
	public static final Resource _BiologicallyDerivedProduct = ResourceFactory
			.createResource(NS + "_BiologicallyDerivedProduct");
	public static final Resource _BiologicallyDerivedProductDispense = ResourceFactory
			.createResource(NS + "_BiologicallyDerivedProductDispense");
	public static final Resource _BodyStructure = ResourceFactory.createResource(NS + "_BodyStructure");
	public static final Resource _CarePlan = ResourceFactory.createResource(NS + "_CarePlan");
	public static final Resource _CareTeam = ResourceFactory.createResource(NS + "_CareTeam");
	public static final Resource _ChargeItem = ResourceFactory.createResource(NS + "_ChargeItem");
	public static final Resource _Claim = ResourceFactory.createResource(NS + "_Claim");
	public static final Resource _ClaimResponse = ResourceFactory.createResource(NS + "_ClaimResponse");
	public static final Resource _ClinicalImpression = ResourceFactory.createResource(NS + "_ClinicalImpression");
	public static final Resource _ClinicalUseDefinition = ResourceFactory.createResource(NS + "_ClinicalUseDefinition");
	public static final Resource _Communication = ResourceFactory.createResource(NS + "_Communication");
	public static final Resource _CommunicationRequest = ResourceFactory.createResource(NS + "_CommunicationRequest");
	public static final Resource _Composition = ResourceFactory.createResource(NS + "_Composition");
	public static final Resource _Condition = ResourceFactory.createResource(NS + "_Condition");
	public static final Resource _Consent = ResourceFactory.createResource(NS + "_Consent");
	public static final Resource _Contract = ResourceFactory.createResource(NS + "_Contract");
	public static final Resource _Coverage = ResourceFactory.createResource(NS + "_Coverage");
	public static final Resource _CoverageEligibilityRequest = ResourceFactory
			.createResource(NS + "_CoverageEligibilityRequest");
	public static final Resource _CoverageEligibilityResponse = ResourceFactory
			.createResource(NS + "_CoverageEligibilityResponse");
	public static final Resource _DetectedIssue = ResourceFactory.createResource(NS + "_DetectedIssue");
	public static final Resource _Device = ResourceFactory.createResource(NS + "_Device");
	public static final Resource _DeviceAssociation = ResourceFactory.createResource(NS + "_DeviceAssociation");
	public static final Resource _DeviceDefinition = ResourceFactory.createResource(NS + "_DeviceDefinition");
	public static final Resource _DeviceDispense = ResourceFactory.createResource(NS + "_DeviceDispense");
	public static final Resource _DeviceMetric = ResourceFactory.createResource(NS + "_DeviceMetric");
	public static final Resource _DeviceRequest = ResourceFactory.createResource(NS + "_DeviceRequest");
	public static final Resource _DeviceUsage = ResourceFactory.createResource(NS + "_DeviceUsage");
	public static final Resource _DiagnosticReport = ResourceFactory.createResource(NS + "_DiagnosticReport");
	public static final Resource _DocumentReference = ResourceFactory.createResource(NS + "_DocumentReference");
	public static final Resource _DomainResource = ResourceFactory.createResource(NS + "_DomainResource");
	public static final Resource _Dosage = ResourceFactory.createResource(NS + "_Dosage");
	public static final Resource _ElementDefinition = ResourceFactory.createResource(NS + "_ElementDefinition");
	public static final Resource _Encounter = ResourceFactory.createResource(NS + "_Encounter");
	public static final Resource _EncounterHistory = ResourceFactory.createResource(NS + "_EncounterHistory");
	public static final Resource _Endpoint = ResourceFactory.createResource(NS + "_Endpoint");
	public static final Resource _EnrollmentRequest = ResourceFactory.createResource(NS + "_EnrollmentRequest");
	public static final Resource _EnrollmentResponse = ResourceFactory.createResource(NS + "_EnrollmentResponse");
	public static final Resource _EpisodeOfCare = ResourceFactory.createResource(NS + "_EpisodeOfCare");
	public static final Resource _ExplanationOfBenefit = ResourceFactory.createResource(NS + "_ExplanationOfBenefit");
	public static final Resource _FamilyMemberHistory = ResourceFactory.createResource(NS + "_FamilyMemberHistory");
	public static final Resource _Flag = ResourceFactory.createResource(NS + "_Flag");
	public static final Resource _FormularyItem = ResourceFactory.createResource(NS + "_FormularyItem");
	public static final Resource _GenomicStudy = ResourceFactory.createResource(NS + "_GenomicStudy");
	public static final Resource _Goal = ResourceFactory.createResource(NS + "_Goal");
	public static final Resource _Group = ResourceFactory.createResource(NS + "_Group");
	public static final Resource _GuidanceResponse = ResourceFactory.createResource(NS + "_GuidanceResponse");
	public static final Resource _HealthcareService = ResourceFactory.createResource(NS + "_HealthcareService");
	public static final Resource _ImagingSelection = ResourceFactory.createResource(NS + "_ImagingSelection");
	public static final Resource _ImagingStudy = ResourceFactory.createResource(NS + "_ImagingStudy");
	public static final Resource _Immunization = ResourceFactory.createResource(NS + "_Immunization");
	public static final Resource _ImmunizationEvaluation = ResourceFactory
			.createResource(NS + "_ImmunizationEvaluation");
	public static final Resource _ImmunizationRecommendation = ResourceFactory
			.createResource(NS + "_ImmunizationRecommendation");
	public static final Resource _Ingredient = ResourceFactory.createResource(NS + "_Ingredient");
	public static final Resource _InsurancePlan = ResourceFactory.createResource(NS + "_InsurancePlan");
	public static final Resource _InventoryItem = ResourceFactory.createResource(NS + "_InventoryItem");
	public static final Resource _InventoryReport = ResourceFactory.createResource(NS + "_InventoryReport");
	public static final Resource _Invoice = ResourceFactory.createResource(NS + "_Invoice");
	public static final Resource _Linkage = ResourceFactory.createResource(NS + "_Linkage");
	public static final Resource _List = ResourceFactory.createResource(NS + "_List");
	public static final Resource _Location = ResourceFactory.createResource(NS + "_Location");
	public static final Resource _ManufacturedItemDefinition = ResourceFactory
			.createResource(NS + "_ManufacturedItemDefinition");
	public static final Resource _MarketingStatus = ResourceFactory.createResource(NS + "_MarketingStatus");
	public static final Resource _MeasureReport = ResourceFactory.createResource(NS + "_MeasureReport");
	public static final Resource _Medication = ResourceFactory.createResource(NS + "_Medication");
	public static final Resource _MedicationAdministration = ResourceFactory
			.createResource(NS + "_MedicationAdministration");
	public static final Resource _MedicationDispense = ResourceFactory.createResource(NS + "_MedicationDispense");
	public static final Resource _MedicationRequest = ResourceFactory.createResource(NS + "_MedicationRequest");
	public static final Resource _MedicationStatement = ResourceFactory.createResource(NS + "_MedicationStatement");
	public static final Resource _MedicinalProductDefinition = ResourceFactory
			.createResource(NS + "_MedicinalProductDefinition");
	public static final Resource _MessageHeader = ResourceFactory.createResource(NS + "_MessageHeader");
	public static final Resource _MolecularSequence = ResourceFactory.createResource(NS + "_MolecularSequence");
	public static final Resource _NutritionIntake = ResourceFactory.createResource(NS + "_NutritionIntake");
	public static final Resource _NutritionOrder = ResourceFactory.createResource(NS + "_NutritionOrder");
	public static final Resource _NutritionProduct = ResourceFactory.createResource(NS + "_NutritionProduct");
	public static final Resource _Observation = ResourceFactory.createResource(NS + "_Observation");
	public static final Resource _OperationOutcome = ResourceFactory.createResource(NS + "_OperationOutcome");
	public static final Resource _Organization = ResourceFactory.createResource(NS + "_Organization");
	public static final Resource _OrganizationAffiliation = ResourceFactory
			.createResource(NS + "_OrganizationAffiliation");
	public static final Resource _PackagedProductDefinition = ResourceFactory
			.createResource(NS + "_PackagedProductDefinition");
	public static final Resource _Patient = ResourceFactory.createResource(NS + "_Patient");
	public static final Resource _PaymentNotice = ResourceFactory.createResource(NS + "_PaymentNotice");
	public static final Resource _PaymentReconciliation = ResourceFactory.createResource(NS + "_PaymentReconciliation");
	public static final Resource _Permission = ResourceFactory.createResource(NS + "_Permission");
	public static final Resource _Person = ResourceFactory.createResource(NS + "_Person");
	public static final Resource _Practitioner = ResourceFactory.createResource(NS + "_Practitioner");
	public static final Resource _PractitionerRole = ResourceFactory.createResource(NS + "_PractitionerRole");
	public static final Resource _Procedure = ResourceFactory.createResource(NS + "_Procedure");
	public static final Resource _ProductShelfLife = ResourceFactory.createResource(NS + "_ProductShelfLife");
	public static final Resource _Provenance = ResourceFactory.createResource(NS + "_Provenance");
	public static final Resource _QuestionnaireResponse = ResourceFactory.createResource(NS + "_QuestionnaireResponse");
	public static final Resource _RegulatedAuthorization = ResourceFactory
			.createResource(NS + "_RegulatedAuthorization");
	public static final Resource _RelatedPerson = ResourceFactory.createResource(NS + "_RelatedPerson");
	public static final Resource _RequestOrchestration = ResourceFactory.createResource(NS + "_RequestOrchestration");
	public static final Resource _ResearchStudy = ResourceFactory.createResource(NS + "_ResearchStudy");
	public static final Resource _ResearchSubject = ResourceFactory.createResource(NS + "_ResearchSubject");
	public static final Resource _RiskAssessment = ResourceFactory.createResource(NS + "_RiskAssessment");
	public static final Resource _Schedule = ResourceFactory.createResource(NS + "_Schedule");
	public static final Resource _ServiceRequest = ResourceFactory.createResource(NS + "_ServiceRequest");
	public static final Resource _Slot = ResourceFactory.createResource(NS + "_Slot");
	public static final Resource _Specimen = ResourceFactory.createResource(NS + "_Specimen");
	public static final Resource _Subscription = ResourceFactory.createResource(NS + "_Subscription");
	public static final Resource _SubscriptionStatus = ResourceFactory.createResource(NS + "_SubscriptionStatus");
	public static final Resource _Substance = ResourceFactory.createResource(NS + "_Substance");
	public static final Resource _SubstanceDefinition = ResourceFactory.createResource(NS + "_SubstanceDefinition");
	public static final Resource _SubstanceNucleicAcid = ResourceFactory.createResource(NS + "_SubstanceNucleicAcid");
	public static final Resource _SubstancePolymer = ResourceFactory.createResource(NS + "_SubstancePolymer");
	public static final Resource _SubstanceProtein = ResourceFactory.createResource(NS + "_SubstanceProtein");
	public static final Resource _SubstanceReferenceInformation = ResourceFactory
			.createResource(NS + "_SubstanceReferenceInformation");
	public static final Resource _SubstanceSourceMaterial = ResourceFactory
			.createResource(NS + "_SubstanceSourceMaterial");
	public static final Resource _SupplyDelivery = ResourceFactory.createResource(NS + "_SupplyDelivery");
	public static final Resource _SupplyRequest = ResourceFactory.createResource(NS + "_SupplyRequest");
	public static final Resource _Task = ResourceFactory.createResource(NS + "_Task");
	public static final Resource _TestReport = ResourceFactory.createResource(NS + "_TestReport");
	public static final Resource _Timing = ResourceFactory.createResource(NS + "_Timing");
	public static final Resource _Transport = ResourceFactory.createResource(NS + "_Transport");
	public static final Resource _VerificationResult = ResourceFactory.createResource(NS + "_VerificationResult");
	public static final Resource _VisionPrescription = ResourceFactory.createResource(NS + "_VisionPrescription");
	public static final Resource _abatement = ResourceFactory.createResource(NS + "_abatement");
	public static final Resource _abnormalCodedValueSet = ResourceFactory.createResource(NS + "_abnormalCodedValueSet");
	public static final Resource _about = ResourceFactory.createResource(NS + "_about");
	public static final Resource _abstract = ResourceFactory.createResource(NS + "_abstract");
	public static final Resource _accept = ResourceFactory.createResource(NS + "_accept");
	public static final Resource _acceptLanguage = ResourceFactory.createResource(NS + "_acceptLanguage");
	public static final Resource _accessionIdentifier = ResourceFactory.createResource(NS + "_accessionIdentifier");
	public static final Resource _accessionNumber = ResourceFactory.createResource(NS + "_accessionNumber");
	public static final Resource _accident = ResourceFactory.createResource(NS + "_accident");
	public static final Resource _account = ResourceFactory.createResource(NS + "_account");
	public static final Resource _accountNumber = ResourceFactory.createResource(NS + "_accountNumber");
	public static final Resource _achievementStatus = ResourceFactory.createResource(NS + "_achievementStatus");
	public static final Resource _action = ResourceFactory.createResource(NS + "_action");
	public static final Resource _active = ResourceFactory.createResource(NS + "_active");
	public static final Resource _activity = ResourceFactory.createResource(NS + "_activity");
	public static final Resource _actor = ResourceFactory.createResource(NS + "_actor");
	public static final Resource _actorId = ResourceFactory.createResource(NS + "_actorId");
	public static final Resource _actual = ResourceFactory.createResource(NS + "_actual");
	public static final Resource _actualComparisonGroup = ResourceFactory.createResource(NS + "_actualComparisonGroup");
	public static final Resource _actualGroup = ResourceFactory.createResource(NS + "_actualGroup");
	public static final Resource _actualNumber = ResourceFactory.createResource(NS + "_actualNumber");
	public static final Resource _actualPeriod = ResourceFactory.createResource(NS + "_actualPeriod");
	public static final Resource _actuality = ResourceFactory.createResource(NS + "_actuality");
	public static final Resource _add = ResourceFactory.createResource(NS + "_add");
	public static final Resource _addItem = ResourceFactory.createResource(NS + "_addItem");
	public static final Resource _additional = ResourceFactory.createResource(NS + "_additional");
	public static final Resource _additionalAttribute = ResourceFactory.createResource(NS + "_additionalAttribute");
	public static final Resource _additionalContext = ResourceFactory.createResource(NS + "_additionalContext");
	public static final Resource _additionalInfo = ResourceFactory.createResource(NS + "_additionalInfo");
	public static final Resource _additionalInstruction = ResourceFactory.createResource(NS + "_additionalInstruction");
	public static final Resource _additionalMonitoringIndicator = ResourceFactory
			.createResource(NS + "_additionalMonitoringIndicator");
	public static final Resource _additionalUse = ResourceFactory.createResource(NS + "_additionalUse");
	public static final Resource _additive = ResourceFactory.createResource(NS + "_additive");
	public static final Resource _address = ResourceFactory.createResource(NS + "_address");
	public static final Resource _addresses = ResourceFactory.createResource(NS + "_addresses");
	public static final Resource _adherence = ResourceFactory.createResource(NS + "_adherence");
	public static final Resource _adjudication = ResourceFactory.createResource(NS + "_adjudication");
	public static final Resource _adjustment = ResourceFactory.createResource(NS + "_adjustment");
	public static final Resource _adjustmentReason = ResourceFactory.createResource(NS + "_adjustmentReason");
	public static final Resource _administeredBy = ResourceFactory.createResource(NS + "_administeredBy");
	public static final Resource _administeredProduct = ResourceFactory.createResource(NS + "_administeredProduct");
	public static final Resource _administrableDoseForm = ResourceFactory.createResource(NS + "_administrableDoseForm");
	public static final Resource _administration = ResourceFactory.createResource(NS + "_administration");
	public static final Resource _administrationInstruction = ResourceFactory
			.createResource(NS + "_administrationInstruction");
	public static final Resource _administrationTreatment = ResourceFactory
			.createResource(NS + "_administrationTreatment");
	public static final Resource _admission = ResourceFactory.createResource(NS + "_admission");
	public static final Resource _admitSource = ResourceFactory.createResource(NS + "_admitSource");
	public static final Resource _affectsState = ResourceFactory.createResource(NS + "_affectsState");
	public static final Resource _affiliation = ResourceFactory.createResource(NS + "_affiliation");
	public static final Resource _age = ResourceFactory.createResource(NS + "_age");
	public static final Resource _agent = ResourceFactory.createResource(NS + "_agent");
	public static final Resource _aggregate = ResourceFactory.createResource(NS + "_aggregate");
	public static final Resource _aggregateMethod = ResourceFactory.createResource(NS + "_aggregateMethod");
	public static final Resource _aggregation = ResourceFactory.createResource(NS + "_aggregation");
	public static final Resource _alias = ResourceFactory.createResource(NS + "_alias");
	public static final Resource _allDay = ResourceFactory.createResource(NS + "_allDay");
	public static final Resource _allergenicIndicator = ResourceFactory.createResource(NS + "_allergenicIndicator");
	public static final Resource _allergyIntolerance = ResourceFactory.createResource(NS + "_allergyIntolerance");
	public static final Resource _allocation = ResourceFactory.createResource(NS + "_allocation");
	public static final Resource _allowed = ResourceFactory.createResource(NS + "_allowed");
	public static final Resource _allowedResponse = ResourceFactory.createResource(NS + "_allowedResponse");
	public static final Resource _allowedType = ResourceFactory.createResource(NS + "_allowedType");
	public static final Resource _alternate = ResourceFactory.createResource(NS + "_alternate");
	public static final Resource _alternateMaterial = ResourceFactory.createResource(NS + "_alternateMaterial");
	public static final Resource _alternative = ResourceFactory.createResource(NS + "_alternative");
	public static final Resource _altitude = ResourceFactory.createResource(NS + "_altitude");
	public static final Resource _amount = ResourceFactory.createResource(NS + "_amount");
	public static final Resource _amountType = ResourceFactory.createResource(NS + "_amountType");
	public static final Resource _analysis = ResourceFactory.createResource(NS + "_analysis");
	public static final Resource _anchor = ResourceFactory.createResource(NS + "_anchor");
	public static final Resource _answer = ResourceFactory.createResource(NS + "_answer");
	public static final Resource _answerConstraint = ResourceFactory.createResource(NS + "_answerConstraint");
	public static final Resource _answerOption = ResourceFactory.createResource(NS + "_answerOption");
	public static final Resource _answerValueSet = ResourceFactory.createResource(NS + "_answerValueSet");
	public static final Resource _any = ResourceFactory.createResource(NS + "_any");
	public static final Resource _applicability = ResourceFactory.createResource(NS + "_applicability");
	public static final Resource _application = ResourceFactory.createResource(NS + "_application");
	public static final Resource _applied = ResourceFactory.createResource(NS + "_applied");
	public static final Resource _applies = ResourceFactory.createResource(NS + "_applies");
	public static final Resource _appliesTo = ResourceFactory.createResource(NS + "_appliesTo");
	public static final Resource _appliesToAll = ResourceFactory.createResource(NS + "_appliesToAll");
	public static final Resource _appointment = ResourceFactory.createResource(NS + "_appointment");
	public static final Resource _appointmentRequired = ResourceFactory.createResource(NS + "_appointmentRequired");
	public static final Resource _appointmentType = ResourceFactory.createResource(NS + "_appointmentType");
	public static final Resource _approvalDate = ResourceFactory.createResource(NS + "_approvalDate");
	public static final Resource _areaOfHybridisation = ResourceFactory.createResource(NS + "_areaOfHybridisation");
	public static final Resource _articleDate = ResourceFactory.createResource(NS + "_articleDate");
	public static final Resource _artifact = ResourceFactory.createResource(NS + "_artifact");
	public static final Resource _artifactAssessment = ResourceFactory.createResource(NS + "_artifactAssessment");
	public static final Resource _asNeeded = ResourceFactory.createResource(NS + "_asNeeded");
	public static final Resource _asNeededFor = ResourceFactory.createResource(NS + "_asNeededFor");
	public static final Resource _assert = ResourceFactory.createResource(NS + "_assert");
	public static final Resource _asserter = ResourceFactory.createResource(NS + "_asserter");
	public static final Resource _assertion = ResourceFactory.createResource(NS + "_assertion");
	public static final Resource _assessment = ResourceFactory.createResource(NS + "_assessment");
	public static final Resource _assessmentMethod = ResourceFactory.createResource(NS + "_assessmentMethod");
	public static final Resource _asset = ResourceFactory.createResource(NS + "_asset");
	public static final Resource _assignedComparisonGroup = ResourceFactory
			.createResource(NS + "_assignedComparisonGroup");
	public static final Resource _assigner = ResourceFactory.createResource(NS + "_assigner");
	public static final Resource _associatedMedication = ResourceFactory.createResource(NS + "_associatedMedication");
	public static final Resource _associatedParty = ResourceFactory.createResource(NS + "_associatedParty");
	public static final Resource _association = ResourceFactory.createResource(NS + "_association");
	public static final Resource _associationType = ResourceFactory.createResource(NS + "_associationType");
	public static final Resource _assurance = ResourceFactory.createResource(NS + "_assurance");
	public static final Resource _attachedDocument = ResourceFactory.createResource(NS + "_attachedDocument");
	public static final Resource _attachment = ResourceFactory.createResource(NS + "_attachment");
	public static final Resource _attestation = ResourceFactory.createResource(NS + "_attestation");
	public static final Resource _attestationSignature = ResourceFactory.createResource(NS + "_attestationSignature");
	public static final Resource _attester = ResourceFactory.createResource(NS + "_attester");
	public static final Resource _attribute = ResourceFactory.createResource(NS + "_attribute");
	public static final Resource _attributeEstimate = ResourceFactory.createResource(NS + "_attributeEstimate");
	public static final Resource _author = ResourceFactory.createResource(NS + "_author");
	public static final Resource _authorDescription = ResourceFactory.createResource(NS + "_authorDescription");
	public static final Resource _authorType = ResourceFactory.createResource(NS + "_authorType");
	public static final Resource _authored = ResourceFactory.createResource(NS + "_authored");
	public static final Resource _authoredOn = ResourceFactory.createResource(NS + "_authoredOn");
	public static final Resource _authoritative = ResourceFactory.createResource(NS + "_authoritative");
	public static final Resource _authority = ResourceFactory.createResource(NS + "_authority");
	public static final Resource _authorization = ResourceFactory.createResource(NS + "_authorization");
	public static final Resource _authorizationRequired = ResourceFactory.createResource(NS + "_authorizationRequired");
	public static final Resource _authorizationSupporting = ResourceFactory
			.createResource(NS + "_authorizationSupporting");
	public static final Resource _authorizationUrl = ResourceFactory.createResource(NS + "_authorizationUrl");
	public static final Resource _authorizingPrescription = ResourceFactory
			.createResource(NS + "_authorizingPrescription");
	public static final Resource _autocreate = ResourceFactory.createResource(NS + "_autocreate");
	public static final Resource _autodelete = ResourceFactory.createResource(NS + "_autodelete");
	public static final Resource _availability = ResourceFactory.createResource(NS + "_availability");
	public static final Resource _availabilityStatus = ResourceFactory.createResource(NS + "_availabilityStatus");
	public static final Resource _availableEndTime = ResourceFactory.createResource(NS + "_availableEndTime");
	public static final Resource _availableStartTime = ResourceFactory.createResource(NS + "_availableStartTime");
	public static final Resource _availableTime = ResourceFactory.createResource(NS + "_availableTime");
	public static final Resource _average = ResourceFactory.createResource(NS + "_average");
	public static final Resource _averageMolecularFormula = ResourceFactory
			.createResource(NS + "_averageMolecularFormula");
	public static final Resource _axis = ResourceFactory.createResource(NS + "_axis");
	public static final Resource _backCurve = ResourceFactory.createResource(NS + "_backCurve");
	public static final Resource _balance = ResourceFactory.createResource(NS + "_balance");
	public static final Resource _base = ResourceFactory.createResource(NS + "_base");
	public static final Resource _baseCitation = ResourceFactory.createResource(NS + "_baseCitation");
	public static final Resource _baseDefinition = ResourceFactory.createResource(NS + "_baseDefinition");
	public static final Resource _baseFormulaProductName = ResourceFactory
			.createResource(NS + "_baseFormulaProductName");
	public static final Resource _baseFormulaType = ResourceFactory.createResource(NS + "_baseFormulaType");
	public static final Resource _baseUnit = ResourceFactory.createResource(NS + "_baseUnit");
	public static final Resource _basedOn = ResourceFactory.createResource(NS + "_basedOn");
	public static final Resource _basis = ResourceFactory.createResource(NS + "_basis");
	public static final Resource _batch = ResourceFactory.createResource(NS + "_batch");
	public static final Resource _beneficiary = ResourceFactory.createResource(NS + "_beneficiary");
	public static final Resource _benefit = ResourceFactory.createResource(NS + "_benefit");
	public static final Resource _benefitBalance = ResourceFactory.createResource(NS + "_benefitBalance");
	public static final Resource _benefitPeriod = ResourceFactory.createResource(NS + "_benefitPeriod");
	public static final Resource _billablePeriod = ResourceFactory.createResource(NS + "_billablePeriod");
	public static final Resource _billingStatus = ResourceFactory.createResource(NS + "_billingStatus");
	public static final Resource _binding = ResourceFactory.createResource(NS + "_binding");
	public static final Resource _biologicalSourceEvent = ResourceFactory.createResource(NS + "_biologicalSourceEvent");
	public static final Resource _birthDate = ResourceFactory.createResource(NS + "_birthDate");
	public static final Resource _bodyLandmarkOrientation = ResourceFactory
			.createResource(NS + "_bodyLandmarkOrientation");
	public static final Resource _bodySite = ResourceFactory.createResource(NS + "_bodySite");
	public static final Resource _bodyStructure = ResourceFactory.createResource(NS + "_bodyStructure");
	public static final Resource _bodysite = ResourceFactory.createResource(NS + "_bodysite");
	public static final Resource _born = ResourceFactory.createResource(NS + "_born");
	public static final Resource _bounds = ResourceFactory.createResource(NS + "_bounds");
	public static final Resource _brand = ResourceFactory.createResource(NS + "_brand");
	public static final Resource _businessArrangement = ResourceFactory.createResource(NS + "_businessArrangement");
	public static final Resource _businessStatus = ResourceFactory.createResource(NS + "_businessStatus");
	public static final Resource _cTerminalModification = ResourceFactory.createResource(NS + "_cTerminalModification");
	public static final Resource _cTerminalModificationId = ResourceFactory
			.createResource(NS + "_cTerminalModificationId");
	public static final Resource _calculatedAt = ResourceFactory.createResource(NS + "_calculatedAt");
	public static final Resource _calibration = ResourceFactory.createResource(NS + "_calibration");
	public static final Resource _caloricDensity = ResourceFactory.createResource(NS + "_caloricDensity");
	public static final Resource _canFilterBy = ResourceFactory.createResource(NS + "_canFilterBy");
	public static final Resource _canPushUpdates = ResourceFactory.createResource(NS + "_canPushUpdates");
	public static final Resource _cancellationDate = ResourceFactory.createResource(NS + "_cancellationDate");
	public static final Resource _cancellationReason = ResourceFactory.createResource(NS + "_cancellationReason");
	public static final Resource _cancelledReason = ResourceFactory.createResource(NS + "_cancelledReason");
	public static final Resource _candidate = ResourceFactory.createResource(NS + "_candidate");
	public static final Resource _cap = ResourceFactory.createResource(NS + "_cap");
	public static final Resource _capabilities = ResourceFactory.createResource(NS + "_capabilities");
	public static final Resource _capability = ResourceFactory.createResource(NS + "_capability");
	public static final Resource _capacity = ResourceFactory.createResource(NS + "_capacity");
	public static final Resource _cardBrand = ResourceFactory.createResource(NS + "_cardBrand");
	public static final Resource _cardinalityBehavior = ResourceFactory.createResource(NS + "_cardinalityBehavior");
	public static final Resource _careManager = ResourceFactory.createResource(NS + "_careManager");
	public static final Resource _careTeam = ResourceFactory.createResource(NS + "_careTeam");
	public static final Resource _careTeamSequence = ResourceFactory.createResource(NS + "_careTeamSequence");
	public static final Resource _carrierAIDC = ResourceFactory.createResource(NS + "_carrierAIDC");
	public static final Resource _carrierHRF = ResourceFactory.createResource(NS + "_carrierHRF");
	public static final Resource _case = ResourceFactory.createResource(NS + "_case");
	public static final Resource _caseSensitive = ResourceFactory.createResource(NS + "_caseSensitive");
	public static final Resource _category = ResourceFactory.createResource(NS + "_category");
	public static final Resource _causality = ResourceFactory.createResource(NS + "_causality");
	public static final Resource _cause = ResourceFactory.createResource(NS + "_cause");
	public static final Resource _certainty = ResourceFactory.createResource(NS + "_certainty");
	public static final Resource _chain = ResourceFactory.createResource(NS + "_chain");
	public static final Resource _changePattern = ResourceFactory.createResource(NS + "_changePattern");
	public static final Resource _changeType = ResourceFactory.createResource(NS + "_changeType");
	public static final Resource _channelType = ResourceFactory.createResource(NS + "_channelType");
	public static final Resource _characteristic = ResourceFactory.createResource(NS + "_characteristic");
	public static final Resource _characteristicType = ResourceFactory.createResource(NS + "_characteristicType");
	public static final Resource _characterization = ResourceFactory.createResource(NS + "_characterization");
	public static final Resource _chargeItem = ResourceFactory.createResource(NS + "_chargeItem");
	public static final Resource _chargeItemCode = ResourceFactory.createResource(NS + "_chargeItemCode");
	public static final Resource _check = ResourceFactory.createResource(NS + "_check");
	public static final Resource _chromosome = ResourceFactory.createResource(NS + "_chromosome");
	public static final Resource _citation = ResourceFactory.createResource(NS + "_citation");
	public static final Resource _citeAs = ResourceFactory.createResource(NS + "_citeAs");
	public static final Resource _citedArtifact = ResourceFactory.createResource(NS + "_citedArtifact");
	public static final Resource _citedMedium = ResourceFactory.createResource(NS + "_citedMedium");
	public static final Resource _city = ResourceFactory.createResource(NS + "_city");
	public static final Resource _claim = ResourceFactory.createResource(NS + "_claim");
	public static final Resource _claimResponse = ResourceFactory.createResource(NS + "_claimResponse");
	public static final Resource _class = ResourceFactory.createResource(NS + "_class");
	public static final Resource _classification = ResourceFactory.createResource(NS + "_classification");
	public static final Resource _classifier = ResourceFactory.createResource(NS + "_classifier");
	public static final Resource _clinicalRecommendationStatement = ResourceFactory
			.createResource(NS + "_clinicalRecommendationStatement");
	public static final Resource _clinicalStatus = ResourceFactory.createResource(NS + "_clinicalStatus");
	public static final Resource _clinicalTrial = ResourceFactory.createResource(NS + "_clinicalTrial");
	public static final Resource _clinicalUseIssue = ResourceFactory.createResource(NS + "_clinicalUseIssue");
	public static final Resource _clockFacePosition = ResourceFactory.createResource(NS + "_clockFacePosition");
	public static final Resource _closure = ResourceFactory.createResource(NS + "_closure");
	public static final Resource _code = ResourceFactory.createResource(NS + "_code");
	public static final Resource _codeFilter = ResourceFactory.createResource(NS + "_codeFilter");
	public static final Resource _codeMap = ResourceFactory.createResource(NS + "_codeMap");
	public static final Resource _codeSearch = ResourceFactory.createResource(NS + "_codeSearch");
	public static final Resource _codeSystem = ResourceFactory.createResource(NS + "_codeSystem");
	public static final Resource _coding = ResourceFactory.createResource(NS + "_coding");
	public static final Resource _collected = ResourceFactory.createResource(NS + "_collected");
	public static final Resource _collection = ResourceFactory.createResource(NS + "_collection");
	public static final Resource _collector = ResourceFactory.createResource(NS + "_collector");
	public static final Resource _color = ResourceFactory.createResource(NS + "_color");
	public static final Resource _combined = ResourceFactory.createResource(NS + "_combined");
	public static final Resource _combinedPharmaceuticalDoseForm = ResourceFactory
			.createResource(NS + "_combinedPharmaceuticalDoseForm");
	public static final Resource _combining = ResourceFactory.createResource(NS + "_combining");
	public static final Resource _comment = ResourceFactory.createResource(NS + "_comment");
	public static final Resource _communication = ResourceFactory.createResource(NS + "_communication");
	public static final Resource _communicationMethod = ResourceFactory.createResource(NS + "_communicationMethod");
	public static final Resource _communicationRequest = ResourceFactory.createResource(NS + "_communicationRequest");
	public static final Resource _comorbidity = ResourceFactory.createResource(NS + "_comorbidity");
	public static final Resource _comparator = ResourceFactory.createResource(NS + "_comparator");
	public static final Resource _compareToSourceExpression = ResourceFactory
			.createResource(NS + "_compareToSourceExpression");
	public static final Resource _compareToSourceId = ResourceFactory.createResource(NS + "_compareToSourceId");
	public static final Resource _compareToSourcePath = ResourceFactory.createResource(NS + "_compareToSourcePath");
	public static final Resource _comparisonGroup = ResourceFactory.createResource(NS + "_comparisonGroup");
	public static final Resource _compartment = ResourceFactory.createResource(NS + "_compartment");
	public static final Resource _complete = ResourceFactory.createResource(NS + "_complete");
	public static final Resource _completionTime = ResourceFactory.createResource(NS + "_completionTime");
	public static final Resource _complication = ResourceFactory.createResource(NS + "_complication");
	public static final Resource _component = ResourceFactory.createResource(NS + "_component");
	public static final Resource _componentPart = ResourceFactory.createResource(NS + "_componentPart");
	public static final Resource _compose = ResourceFactory.createResource(NS + "_compose");
	public static final Resource _compositeScoring = ResourceFactory.createResource(NS + "_compositeScoring");
	public static final Resource _composition = ResourceFactory.createResource(NS + "_composition");
	public static final Resource _compositional = ResourceFactory.createResource(NS + "_compositional");
	public static final Resource _comprisedOf = ResourceFactory.createResource(NS + "_comprisedOf");
	public static final Resource _concentration = ResourceFactory.createResource(NS + "_concentration");
	public static final Resource _concept = ResourceFactory.createResource(NS + "_concept");
	public static final Resource _conclusion = ResourceFactory.createResource(NS + "_conclusion");
	public static final Resource _conclusionCode = ResourceFactory.createResource(NS + "_conclusionCode");
	public static final Resource _condition = ResourceFactory.createResource(NS + "_condition");
	public static final Resource _conditionalCreate = ResourceFactory.createResource(NS + "_conditionalCreate");
	public static final Resource _conditionalDelete = ResourceFactory.createResource(NS + "_conditionalDelete");
	public static final Resource _conditionalPatch = ResourceFactory.createResource(NS + "_conditionalPatch");
	public static final Resource _conditionalRead = ResourceFactory.createResource(NS + "_conditionalRead");
	public static final Resource _conditionalUpdate = ResourceFactory.createResource(NS + "_conditionalUpdate");
	public static final Resource _conditionality = ResourceFactory.createResource(NS + "_conditionality");
	public static final Resource _confidentialityIndicator = ResourceFactory
			.createResource(NS + "_confidentialityIndicator");
	public static final Resource _conformance = ResourceFactory.createResource(NS + "_conformance");
	public static final Resource _conformsTo = ResourceFactory.createResource(NS + "_conformsTo");
	public static final Resource _connectionType = ResourceFactory.createResource(NS + "_connectionType");
	public static final Resource _connectivity = ResourceFactory.createResource(NS + "_connectivity");
	public static final Resource _consent = ResourceFactory.createResource(NS + "_consent");
	public static final Resource _const = ResourceFactory.createResource(NS + "_const");
	public static final Resource _constituent = ResourceFactory.createResource(NS + "_constituent");
	public static final Resource _constraint = ResourceFactory.createResource(NS + "_constraint");
	public static final Resource _consumedItem = ResourceFactory.createResource(NS + "_consumedItem");
	public static final Resource _contact = ResourceFactory.createResource(NS + "_contact");
	public static final Resource _contained = ResourceFactory.createResource(NS + "_contained");
	public static final Resource _containedInstance = ResourceFactory.createResource(NS + "_containedInstance");
	public static final Resource _containedItem = ResourceFactory.createResource(NS + "_containedItem");
	public static final Resource _containedItemQuantity = ResourceFactory.createResource(NS + "_containedItemQuantity");
	public static final Resource _container = ResourceFactory.createResource(NS + "_container");
	public static final Resource _contains = ResourceFactory.createResource(NS + "_contains");
	public static final Resource _content = ResourceFactory.createResource(NS + "_content");
	public static final Resource _contentDefinition = ResourceFactory.createResource(NS + "_contentDefinition");
	public static final Resource _contentDerivative = ResourceFactory.createResource(NS + "_contentDerivative");
	public static final Resource _contentReference = ResourceFactory.createResource(NS + "_contentReference");
	public static final Resource _contentType = ResourceFactory.createResource(NS + "_contentType");
	public static final Resource _context = ResourceFactory.createResource(NS + "_context");
	public static final Resource _contextInvariant = ResourceFactory.createResource(NS + "_contextInvariant");
	public static final Resource _contextLinkId = ResourceFactory.createResource(NS + "_contextLinkId");
	public static final Resource _continuous = ResourceFactory.createResource(NS + "_continuous");
	public static final Resource _contract = ResourceFactory.createResource(NS + "_contract");
	public static final Resource _contraindicatedVaccineCode = ResourceFactory
			.createResource(NS + "_contraindicatedVaccineCode");
	public static final Resource _contraindication = ResourceFactory.createResource(NS + "_contraindication");
	public static final Resource _contributedToDeath = ResourceFactory.createResource(NS + "_contributedToDeath");
	public static final Resource _contributingFactor = ResourceFactory.createResource(NS + "_contributingFactor");
	public static final Resource _contributionInstance = ResourceFactory.createResource(NS + "_contributionInstance");
	public static final Resource _contributionType = ResourceFactory.createResource(NS + "_contributionType");
	public static final Resource _contributor = ResourceFactory.createResource(NS + "_contributor");
	public static final Resource _contributorship = ResourceFactory.createResource(NS + "_contributorship");
	public static final Resource _control = ResourceFactory.createResource(NS + "_control");
	public static final Resource _controller = ResourceFactory.createResource(NS + "_controller");
	public static final Resource _coordinate = ResourceFactory.createResource(NS + "_coordinate");
	public static final Resource _coordinateSystem = ResourceFactory.createResource(NS + "_coordinateSystem");
	public static final Resource _copackagedIndicator = ResourceFactory.createResource(NS + "_copackagedIndicator");
	public static final Resource _copolymerConnectivity = ResourceFactory.createResource(NS + "_copolymerConnectivity");
	public static final Resource _copyright = ResourceFactory.createResource(NS + "_copyright");
	public static final Resource _copyrightLabel = ResourceFactory.createResource(NS + "_copyrightLabel");
	public static final Resource _correctiveAction = ResourceFactory.createResource(NS + "_correctiveAction");
	public static final Resource _correspondingContact = ResourceFactory.createResource(NS + "_correspondingContact");
	public static final Resource _cors = ResourceFactory.createResource(NS + "_cors");
	public static final Resource _cost = ResourceFactory.createResource(NS + "_cost");
	public static final Resource _costCenter = ResourceFactory.createResource(NS + "_costCenter");
	public static final Resource _costToBeneficiary = ResourceFactory.createResource(NS + "_costToBeneficiary");
	public static final Resource _count = ResourceFactory.createResource(NS + "_count");
	public static final Resource _countMax = ResourceFactory.createResource(NS + "_countMax");
	public static final Resource _countType = ResourceFactory.createResource(NS + "_countType");
	public static final Resource _countingDateTime = ResourceFactory.createResource(NS + "_countingDateTime");
	public static final Resource _country = ResourceFactory.createResource(NS + "_country");
	public static final Resource _countryOfOrigin = ResourceFactory.createResource(NS + "_countryOfOrigin");
	public static final Resource _courseOfTherapyType = ResourceFactory.createResource(NS + "_courseOfTherapyType");
	public static final Resource _coverage = ResourceFactory.createResource(NS + "_coverage");
	public static final Resource _coverageArea = ResourceFactory.createResource(NS + "_coverageArea");
	public static final Resource _covers = ResourceFactory.createResource(NS + "_covers");
	public static final Resource _created = ResourceFactory.createResource(NS + "_created");
	public static final Resource _creation = ResourceFactory.createResource(NS + "_creation");
	public static final Resource _criteria = ResourceFactory.createResource(NS + "_criteria");
	public static final Resource _criticalCodedValueSet = ResourceFactory.createResource(NS + "_criticalCodedValueSet");
	public static final Resource _criticality = ResourceFactory.createResource(NS + "_criticality");
	public static final Resource _crossReference = ResourceFactory.createResource(NS + "_crossReference");
	public static final Resource _currency = ResourceFactory.createResource(NS + "_currency");
	public static final Resource _current = ResourceFactory.createResource(NS + "_current");
	public static final Resource _currentLocation = ResourceFactory.createResource(NS + "_currentLocation");
	public static final Resource _currentState = ResourceFactory.createResource(NS + "_currentState");
	public static final Resource _custodian = ResourceFactory.createResource(NS + "_custodian");
	public static final Resource _cycle = ResourceFactory.createResource(NS + "_cycle");
	public static final Resource _cylinder = ResourceFactory.createResource(NS + "_cylinder");
	public static final Resource _data = ResourceFactory.createResource(NS + "_data");
	public static final Resource _dataAbsentReason = ResourceFactory.createResource(NS + "_dataAbsentReason");
	public static final Resource _dataPeriod = ResourceFactory.createResource(NS + "_dataPeriod");
	public static final Resource _dataRequirement = ResourceFactory.createResource(NS + "_dataRequirement");
	public static final Resource _dataUpdateType = ResourceFactory.createResource(NS + "_dataUpdateType");
	public static final Resource _date = ResourceFactory.createResource(NS + "_date");
	public static final Resource _dateAccessed = ResourceFactory.createResource(NS + "_dateAccessed");
	public static final Resource _dateAsserted = ResourceFactory.createResource(NS + "_dateAsserted");
	public static final Resource _dateCriterion = ResourceFactory.createResource(NS + "_dateCriterion");
	public static final Resource _dateFilter = ResourceFactory.createResource(NS + "_dateFilter");
	public static final Resource _dateOfDiagnosis = ResourceFactory.createResource(NS + "_dateOfDiagnosis");
	public static final Resource _dateOfService = ResourceFactory.createResource(NS + "_dateOfService");
	public static final Resource _dateRange = ResourceFactory.createResource(NS + "_dateRange");
	public static final Resource _dateTime = ResourceFactory.createResource(NS + "_dateTime");
	public static final Resource _dateWritten = ResourceFactory.createResource(NS + "_dateWritten");
	public static final Resource _dayOfMonth = ResourceFactory.createResource(NS + "_dayOfMonth");
	public static final Resource _dayOfWeek = ResourceFactory.createResource(NS + "_dayOfWeek");
	public static final Resource _daysOfWeek = ResourceFactory.createResource(NS + "_daysOfWeek");
	public static final Resource _daysSupply = ResourceFactory.createResource(NS + "_daysSupply");
	public static final Resource _deceased = ResourceFactory.createResource(NS + "_deceased");
	public static final Resource _decision = ResourceFactory.createResource(NS + "_decision");
	public static final Resource _decisionMode = ResourceFactory.createResource(NS + "_decisionMode");
	public static final Resource _defaultManualCompletion = ResourceFactory
			.createResource(NS + "_defaultManualCompletion");
	public static final Resource _defaultValue = ResourceFactory.createResource(NS + "_defaultValue");
	public static final Resource _definition = ResourceFactory.createResource(NS + "_definition");
	public static final Resource _definitionByCombination = ResourceFactory
			.createResource(NS + "_definitionByCombination");
	public static final Resource _definitionByTypeAndValue = ResourceFactory
			.createResource(NS + "_definitionByTypeAndValue");
	public static final Resource _definitionCanonical = ResourceFactory.createResource(NS + "_definitionCanonical");
	public static final Resource _definitionCodeableConcept = ResourceFactory
			.createResource(NS + "_definitionCodeableConcept");
	public static final Resource _definitionExpression = ResourceFactory.createResource(NS + "_definitionExpression");
	public static final Resource _definitionId = ResourceFactory.createResource(NS + "_definitionId");
	public static final Resource _definitionReference = ResourceFactory.createResource(NS + "_definitionReference");
	public static final Resource _definitionUri = ResourceFactory.createResource(NS + "_definitionUri");
	public static final Resource _definitional = ResourceFactory.createResource(NS + "_definitional");
	public static final Resource _degreeOfPolymerisation = ResourceFactory
			.createResource(NS + "_degreeOfPolymerisation");
	public static final Resource _deleted = ResourceFactory.createResource(NS + "_deleted");
	public static final Resource _deliverFor = ResourceFactory.createResource(NS + "_deliverFor");
	public static final Resource _deliverFrom = ResourceFactory.createResource(NS + "_deliverFrom");
	public static final Resource _deliverTo = ResourceFactory.createResource(NS + "_deliverTo");
	public static final Resource _deliveryDevice = ResourceFactory.createResource(NS + "_deliveryDevice");
	public static final Resource _denominator = ResourceFactory.createResource(NS + "_denominator");
	public static final Resource _dependency = ResourceFactory.createResource(NS + "_dependency");
	public static final Resource _dependent = ResourceFactory.createResource(NS + "_dependent");
	public static final Resource _dependsOn = ResourceFactory.createResource(NS + "_dependsOn");
	public static final Resource _derivation = ResourceFactory.createResource(NS + "_derivation");
	public static final Resource _derivedFrom = ResourceFactory.createResource(NS + "_derivedFrom");
	public static final Resource _derivedFromCanonical = ResourceFactory.createResource(NS + "_derivedFromCanonical");
	public static final Resource _derivedFromUri = ResourceFactory.createResource(NS + "_derivedFromUri");
	public static final Resource _description = ResourceFactory.createResource(NS + "_description");
	public static final Resource _descriptionSummary = ResourceFactory.createResource(NS + "_descriptionSummary");
	public static final Resource _designation = ResourceFactory.createResource(NS + "_designation");
	public static final Resource _destination = ResourceFactory.createResource(NS + "_destination");
	public static final Resource _detail = ResourceFactory.createResource(NS + "_detail");
	public static final Resource _detailSequence = ResourceFactory.createResource(NS + "_detailSequence");
	public static final Resource _details = ResourceFactory.createResource(NS + "_details");
	public static final Resource _detected = ResourceFactory.createResource(NS + "_detected");
	public static final Resource _developmentStage = ResourceFactory.createResource(NS + "_developmentStage");
	public static final Resource _device = ResourceFactory.createResource(NS + "_device");
	public static final Resource _deviceIdentifier = ResourceFactory.createResource(NS + "_deviceIdentifier");
	public static final Resource _deviceName = ResourceFactory.createResource(NS + "_deviceName");
	public static final Resource _diagnosis = ResourceFactory.createResource(NS + "_diagnosis");
	public static final Resource _diagnosisRelatedGroup = ResourceFactory.createResource(NS + "_diagnosisRelatedGroup");
	public static final Resource _diagnosisSequence = ResourceFactory.createResource(NS + "_diagnosisSequence");
	public static final Resource _diagnostics = ResourceFactory.createResource(NS + "_diagnostics");
	public static final Resource _diameter = ResourceFactory.createResource(NS + "_diameter");
	public static final Resource _dietPreference = ResourceFactory.createResource(NS + "_dietPreference");
	public static final Resource _differential = ResourceFactory.createResource(NS + "_differential");
	public static final Resource _dimensions = ResourceFactory.createResource(NS + "_dimensions");
	public static final Resource _direction = ResourceFactory.createResource(NS + "_direction");
	public static final Resource _directnessMatch = ResourceFactory.createResource(NS + "_directnessMatch");
	public static final Resource _disabledDisplay = ResourceFactory.createResource(NS + "_disabledDisplay");
	public static final Resource _dischargeDisposition = ResourceFactory.createResource(NS + "_dischargeDisposition");
	public static final Resource _disclaimer = ResourceFactory.createResource(NS + "_disclaimer");
	public static final Resource _discriminator = ResourceFactory.createResource(NS + "_discriminator");
	public static final Resource _diseaseStatus = ResourceFactory.createResource(NS + "_diseaseStatus");
	public static final Resource _diseaseSymptomProcedure = ResourceFactory
			.createResource(NS + "_diseaseSymptomProcedure");
	public static final Resource _dispenseInterval = ResourceFactory.createResource(NS + "_dispenseInterval");
	public static final Resource _dispenseRequest = ResourceFactory.createResource(NS + "_dispenseRequest");
	public static final Resource _dispenser = ResourceFactory.createResource(NS + "_dispenser");
	public static final Resource _dispenserInstruction = ResourceFactory.createResource(NS + "_dispenserInstruction");
	public static final Resource _display = ResourceFactory.createResource(NS + "_display");
	public static final Resource _displayName = ResourceFactory.createResource(NS + "_displayName");
	public static final Resource _disposition = ResourceFactory.createResource(NS + "_disposition");
	public static final Resource _distanceFromLandmark = ResourceFactory.createResource(NS + "_distanceFromLandmark");
	public static final Resource _distributor = ResourceFactory.createResource(NS + "_distributor");
	public static final Resource _district = ResourceFactory.createResource(NS + "_district");
	public static final Resource _disulfideLinkage = ResourceFactory.createResource(NS + "_disulfideLinkage");
	public static final Resource _div = ResourceFactory.createResource(NS + "_div");
	public static final Resource _division = ResourceFactory.createResource(NS + "_division");
	public static final Resource _doNotPerform = ResourceFactory.createResource(NS + "_doNotPerform");
	public static final Resource _docStatus = ResourceFactory.createResource(NS + "_docStatus");
	public static final Resource _document = ResourceFactory.createResource(NS + "_document");
	public static final Resource _documentType = ResourceFactory.createResource(NS + "_documentType");
	public static final Resource _documentation = ResourceFactory.createResource(NS + "_documentation");
	public static final Resource _domain = ResourceFactory.createResource(NS + "_domain");
	public static final Resource _dosage = ResourceFactory.createResource(NS + "_dosage");
	public static final Resource _dosageInstruction = ResourceFactory.createResource(NS + "_dosageInstruction");
	public static final Resource _dose = ResourceFactory.createResource(NS + "_dose");
	public static final Resource _doseAdministrationAid = ResourceFactory.createResource(NS + "_doseAdministrationAid");
	public static final Resource _doseAndRate = ResourceFactory.createResource(NS + "_doseAndRate");
	public static final Resource _doseForm = ResourceFactory.createResource(NS + "_doseForm");
	public static final Resource _doseNumber = ResourceFactory.createResource(NS + "_doseNumber");
	public static final Resource _doseQuantity = ResourceFactory.createResource(NS + "_doseQuantity");
	public static final Resource _doseStatus = ResourceFactory.createResource(NS + "_doseStatus");
	public static final Resource _doseStatusReason = ResourceFactory.createResource(NS + "_doseStatusReason");
	public static final Resource _dosingGuideline = ResourceFactory.createResource(NS + "_dosingGuideline");
	public static final Resource _drugCharacteristic = ResourceFactory.createResource(NS + "_drugCharacteristic");
	public static final Resource _due = ResourceFactory.createResource(NS + "_due");
	public static final Resource _duration = ResourceFactory.createResource(NS + "_duration");
	public static final Resource _durationMax = ResourceFactory.createResource(NS + "_durationMax");
	public static final Resource _durationUnit = ResourceFactory.createResource(NS + "_durationUnit");
	public static final Resource _during = ResourceFactory.createResource(NS + "_during");
	public static final Resource _dynamicValue = ResourceFactory.createResource(NS + "_dynamicValue");
	public static final Resource _edit = ResourceFactory.createResource(NS + "_edit");
	public static final Resource _editor = ResourceFactory.createResource(NS + "_editor");
	public static final Resource _effect = ResourceFactory.createResource(NS + "_effect");
	public static final Resource _effective = ResourceFactory.createResource(NS + "_effective");
	public static final Resource _effectiveDate = ResourceFactory.createResource(NS + "_effectiveDate");
	public static final Resource _effectiveDosePeriod = ResourceFactory.createResource(NS + "_effectiveDosePeriod");
	public static final Resource _effectivePeriod = ResourceFactory.createResource(NS + "_effectivePeriod");
	public static final Resource _effectiveTime = ResourceFactory.createResource(NS + "_effectiveTime");
	public static final Resource _element = ResourceFactory.createResource(NS + "_element");
	public static final Resource _eligibility = ResourceFactory.createResource(NS + "_eligibility");
	public static final Resource _emptyReason = ResourceFactory.createResource(NS + "_emptyReason");
	public static final Resource _enableBehavior = ResourceFactory.createResource(NS + "_enableBehavior");
	public static final Resource _enableWhen = ResourceFactory.createResource(NS + "_enableWhen");
	public static final Resource _encodeRequestUrl = ResourceFactory.createResource(NS + "_encodeRequestUrl");
	public static final Resource _encounter = ResourceFactory.createResource(NS + "_encounter");
	public static final Resource _end = ResourceFactory.createResource(NS + "_end");
	public static final Resource _endDate = ResourceFactory.createResource(NS + "_endDate");
	public static final Resource _endParam = ResourceFactory.createResource(NS + "_endParam");
	public static final Resource _endRelationship = ResourceFactory.createResource(NS + "_endRelationship");
	public static final Resource _endorser = ResourceFactory.createResource(NS + "_endorser");
	public static final Resource _endpoint = ResourceFactory.createResource(NS + "_endpoint");
	public static final Resource _enteralFormula = ResourceFactory.createResource(NS + "_enteralFormula");
	public static final Resource _enteredDate = ResourceFactory.createResource(NS + "_enteredDate");
	public static final Resource _enterer = ResourceFactory.createResource(NS + "_enterer");
	public static final Resource _entity = ResourceFactory.createResource(NS + "_entity");
	public static final Resource _entityRelatedness = ResourceFactory.createResource(NS + "_entityRelatedness");
	public static final Resource _entry = ResourceFactory.createResource(NS + "_entry");
	public static final Resource _entryClassifier = ResourceFactory.createResource(NS + "_entryClassifier");
	public static final Resource _entryQuantity = ResourceFactory.createResource(NS + "_entryQuantity");
	public static final Resource _entryReference = ResourceFactory.createResource(NS + "_entryReference");
	public static final Resource _entryType = ResourceFactory.createResource(NS + "_entryType");
	public static final Resource _environmentType = ResourceFactory.createResource(NS + "_environmentType");
	public static final Resource _environmentalSetting = ResourceFactory.createResource(NS + "_environmentalSetting");
	public static final Resource _episodeOfCare = ResourceFactory.createResource(NS + "_episodeOfCare");
	public static final Resource _error = ResourceFactory.createResource(NS + "_error");
	public static final Resource _estimate = ResourceFactory.createResource(NS + "_estimate");
	public static final Resource _estimatedAge = ResourceFactory.createResource(NS + "_estimatedAge");
	public static final Resource _etag = ResourceFactory.createResource(NS + "_etag");
	public static final Resource _evaluatedResource = ResourceFactory.createResource(NS + "_evaluatedResource");
	public static final Resource _evaluationMessage = ResourceFactory.createResource(NS + "_evaluationMessage");
	public static final Resource _event = ResourceFactory.createResource(NS + "_event");
	public static final Resource _eventHistory = ResourceFactory.createResource(NS + "_eventHistory");
	public static final Resource _eventNumber = ResourceFactory.createResource(NS + "_eventNumber");
	public static final Resource _eventTrigger = ResourceFactory.createResource(NS + "_eventTrigger");
	public static final Resource _eventsSinceSubscriptionStart = ResourceFactory
			.createResource(NS + "_eventsSinceSubscriptionStart");
	public static final Resource _evidence = ResourceFactory.createResource(NS + "_evidence");
	public static final Resource _example = ResourceFactory.createResource(NS + "_example");
	public static final Resource _exception = ResourceFactory.createResource(NS + "_exception");
	public static final Resource _exclude = ResourceFactory.createResource(NS + "_exclude");
	public static final Resource _excludeFoodModifier = ResourceFactory.createResource(NS + "_excludeFoodModifier");
	public static final Resource _excluded = ResourceFactory.createResource(NS + "_excluded");
	public static final Resource _excludedStructure = ResourceFactory.createResource(NS + "_excludedStructure");
	public static final Resource _excludingDate = ResourceFactory.createResource(NS + "_excludingDate");
	public static final Resource _excludingRecurrenceId = ResourceFactory.createResource(NS + "_excludingRecurrenceId");
	public static final Resource _exclusionCriteria = ResourceFactory.createResource(NS + "_exclusionCriteria");
	public static final Resource _executionPeriod = ResourceFactory.createResource(NS + "_executionPeriod");
	public static final Resource _exitCriteria = ResourceFactory.createResource(NS + "_exitCriteria");
	public static final Resource _expansion = ResourceFactory.createResource(NS + "_expansion");
	public static final Resource _expectedInResearchStudy = ResourceFactory
			.createResource(NS + "_expectedInResearchStudy");
	public static final Resource _expectedSupplyDuration = ResourceFactory
			.createResource(NS + "_expectedSupplyDuration");
	public static final Resource _experimental = ResourceFactory.createResource(NS + "_experimental");
	public static final Resource _expirationDate = ResourceFactory.createResource(NS + "_expirationDate");
	public static final Resource _expirationType = ResourceFactory.createResource(NS + "_expirationType");
	public static final Resource _expiry = ResourceFactory.createResource(NS + "_expiry");
	public static final Resource _exposureRoute = ResourceFactory.createResource(NS + "_exposureRoute");
	public static final Resource _expression = ResourceFactory.createResource(NS + "_expression");
	public static final Resource _extends = ResourceFactory.createResource(NS + "_extends");
	public static final Resource _extension = ResourceFactory.createResource(NS + "_extension");
	public static final Resource _extraDetails = ResourceFactory.createResource(NS + "_extraDetails");
	public static final Resource _eye = ResourceFactory.createResource(NS + "_eye");
	public static final Resource _facility = ResourceFactory.createResource(NS + "_facility");
	public static final Resource _facilityType = ResourceFactory.createResource(NS + "_facilityType");
	public static final Resource _factor = ResourceFactory.createResource(NS + "_factor");
	public static final Resource _failureAction = ResourceFactory.createResource(NS + "_failureAction");
	public static final Resource _family = ResourceFactory.createResource(NS + "_family");
	public static final Resource _fastingStatus = ResourceFactory.createResource(NS + "_fastingStatus");
	public static final Resource _feature = ResourceFactory.createResource(NS + "_feature");
	public static final Resource _fhirPathCriteria = ResourceFactory.createResource(NS + "_fhirPathCriteria");
	public static final Resource _fhirVersion = ResourceFactory.createResource(NS + "_fhirVersion");
	public static final Resource _field = ResourceFactory.createResource(NS + "_field");
	public static final Resource _file = ResourceFactory.createResource(NS + "_file");
	public static final Resource _filter = ResourceFactory.createResource(NS + "_filter");
	public static final Resource _filterBy = ResourceFactory.createResource(NS + "_filterBy");
	public static final Resource _filterDefinition = ResourceFactory.createResource(NS + "_filterDefinition");
	public static final Resource _filterParameter = ResourceFactory.createResource(NS + "_filterParameter");
	public static final Resource _financial = ResourceFactory.createResource(NS + "_financial");
	public static final Resource _finding = ResourceFactory.createResource(NS + "_finding");
	public static final Resource _firstDose = ResourceFactory.createResource(NS + "_firstDose");
	public static final Resource _firstPage = ResourceFactory.createResource(NS + "_firstPage");
	public static final Resource _fivePrime = ResourceFactory.createResource(NS + "_fivePrime");
	public static final Resource _fixed = ResourceFactory.createResource(NS + "_fixed");
	public static final Resource _fixture = ResourceFactory.createResource(NS + "_fixture");
	public static final Resource _flag = ResourceFactory.createResource(NS + "_flag");
	public static final Resource _fluidConsistencyType = ResourceFactory.createResource(NS + "_fluidConsistencyType");
	public static final Resource _focal = ResourceFactory.createResource(NS + "_focal");
	public static final Resource _focalDevice = ResourceFactory.createResource(NS + "_focalDevice");
	public static final Resource _focus = ResourceFactory.createResource(NS + "_focus");
	public static final Resource _focusReference = ResourceFactory.createResource(NS + "_focusReference");
	public static final Resource _followUp = ResourceFactory.createResource(NS + "_followUp");
	public static final Resource _foodPreferenceModifier = ResourceFactory
			.createResource(NS + "_foodPreferenceModifier");
	public static final Resource _foodType = ResourceFactory.createResource(NS + "_foodType");
	public static final Resource _for = ResourceFactory.createResource(NS + "_for");
	public static final Resource _forecastReason = ResourceFactory.createResource(NS + "_forecastReason");
	public static final Resource _forecastStatus = ResourceFactory.createResource(NS + "_forecastStatus");
	public static final Resource _forenameInitials = ResourceFactory.createResource(NS + "_forenameInitials");
	public static final Resource _form = ResourceFactory.createResource(NS + "_form");
	public static final Resource _formCode = ResourceFactory.createResource(NS + "_formCode");
	public static final Resource _formOf = ResourceFactory.createResource(NS + "_formOf");
	public static final Resource _format = ResourceFactory.createResource(NS + "_format");
	public static final Resource _formatted = ResourceFactory.createResource(NS + "_formatted");
	public static final Resource _fraction = ResourceFactory.createResource(NS + "_fraction");
	public static final Resource _fractionDescription = ResourceFactory.createResource(NS + "_fractionDescription");
	public static final Resource _frameOfReferenceUid = ResourceFactory.createResource(NS + "_frameOfReferenceUid");
	public static final Resource _frames = ResourceFactory.createResource(NS + "_frames");
	public static final Resource _freeToShare = ResourceFactory.createResource(NS + "_freeToShare");
	public static final Resource _frequency = ResourceFactory.createResource(NS + "_frequency");
	public static final Resource _frequencyMax = ResourceFactory.createResource(NS + "_frequencyMax");
	public static final Resource _frequencyOfOccurrence = ResourceFactory.createResource(NS + "_frequencyOfOccurrence");
	public static final Resource _friday = ResourceFactory.createResource(NS + "_friday");
	public static final Resource _friendly = ResourceFactory.createResource(NS + "_friendly");
	public static final Resource _fullUrl = ResourceFactory.createResource(NS + "_fullUrl");
	public static final Resource _function = ResourceFactory.createResource(NS + "_function");
	public static final Resource _fundingSource = ResourceFactory.createResource(NS + "_fundingSource");
	public static final Resource _fundsReserve = ResourceFactory.createResource(NS + "_fundsReserve");
	public static final Resource _fundsReserveRequested = ResourceFactory.createResource(NS + "_fundsReserveRequested");
	public static final Resource _gateway = ResourceFactory.createResource(NS + "_gateway");
	public static final Resource _gender = ResourceFactory.createResource(NS + "_gender");
	public static final Resource _gene = ResourceFactory.createResource(NS + "_gene");
	public static final Resource _geneElement = ResourceFactory.createResource(NS + "_geneElement");
	public static final Resource _geneSequenceOrigin = ResourceFactory.createResource(NS + "_geneSequenceOrigin");
	public static final Resource _generalCost = ResourceFactory.createResource(NS + "_generalCost");
	public static final Resource _generalPractitioner = ResourceFactory.createResource(NS + "_generalPractitioner");
	public static final Resource _generatedBy = ResourceFactory.createResource(NS + "_generatedBy");
	public static final Resource _generation = ResourceFactory.createResource(NS + "_generation");
	public static final Resource _genomeAssembly = ResourceFactory.createResource(NS + "_genomeAssembly");
	public static final Resource _genomeBuild = ResourceFactory.createResource(NS + "_genomeBuild");
	public static final Resource _genus = ResourceFactory.createResource(NS + "_genus");
	public static final Resource _geographicalLocation = ResourceFactory.createResource(NS + "_geographicalLocation");
	public static final Resource _geometry = ResourceFactory.createResource(NS + "_geometry");
	public static final Resource _gestationalAge = ResourceFactory.createResource(NS + "_gestationalAge");
	public static final Resource _given = ResourceFactory.createResource(NS + "_given");
	public static final Resource _global = ResourceFactory.createResource(NS + "_global");
	public static final Resource _goal = ResourceFactory.createResource(NS + "_goal");
	public static final Resource _goalId = ResourceFactory.createResource(NS + "_goalId");
	public static final Resource _grade = ResourceFactory.createResource(NS + "_grade");
	public static final Resource _grantee = ResourceFactory.createResource(NS + "_grantee");
	public static final Resource _grantor = ResourceFactory.createResource(NS + "_grantor");
	public static final Resource _graph = ResourceFactory.createResource(NS + "_graph");
	public static final Resource _group = ResourceFactory.createResource(NS + "_group");
	public static final Resource _groupDefinition = ResourceFactory.createResource(NS + "_groupDefinition");
	public static final Resource _groupIdentifier = ResourceFactory.createResource(NS + "_groupIdentifier");
	public static final Resource _groupSize = ResourceFactory.createResource(NS + "_groupSize");
	public static final Resource _grouping = ResourceFactory.createResource(NS + "_grouping");
	public static final Resource _groupingBehavior = ResourceFactory.createResource(NS + "_groupingBehavior");
	public static final Resource _groupingId = ResourceFactory.createResource(NS + "_groupingId");
	public static final Resource _guarantor = ResourceFactory.createResource(NS + "_guarantor");
	public static final Resource _guidance = ResourceFactory.createResource(NS + "_guidance");
	public static final Resource _guideline = ResourceFactory.createResource(NS + "_guideline");
	public static final Resource _handling = ResourceFactory.createResource(NS + "_handling");
	public static final Resource _hasBodySite = ResourceFactory.createResource(NS + "_hasBodySite");
	public static final Resource _hasIngredient = ResourceFactory.createResource(NS + "_hasIngredient");
	public static final Resource _hasMember = ResourceFactory.createResource(NS + "_hasMember");
	public static final Resource _hasPart = ResourceFactory.createResource(NS + "_hasPart");
	public static final Resource _hasSeverity = ResourceFactory.createResource(NS + "_hasSeverity");
	public static final Resource _hasStage = ResourceFactory.createResource(NS + "_hasStage");
	public static final Resource _hash = ResourceFactory.createResource(NS + "_hash");
	public static final Resource _header = ResourceFactory.createResource(NS + "_header");
	public static final Resource _headerField = ResourceFactory.createResource(NS + "_headerField");
	public static final Resource _healthcareService = ResourceFactory.createResource(NS + "_healthcareService");
	public static final Resource _heartbeatPeriod = ResourceFactory.createResource(NS + "_heartbeatPeriod");
	public static final Resource _height = ResourceFactory.createResource(NS + "_height");
	public static final Resource _hierarchical = ResourceFactory.createResource(NS + "_hierarchical");
	public static final Resource _hierarchyMeaning = ResourceFactory.createResource(NS + "_hierarchyMeaning");
	public static final Resource _high = ResourceFactory.createResource(NS + "_high");
	public static final Resource _highNumerator = ResourceFactory.createResource(NS + "_highNumerator");
	public static final Resource _hint = ResourceFactory.createResource(NS + "_hint");
	public static final Resource _history = ResourceFactory.createResource(NS + "_history");
	public static final Resource _holder = ResourceFactory.createResource(NS + "_holder");
	public static final Resource _hoursOfOperation = ResourceFactory.createResource(NS + "_hoursOfOperation");
	public static final Resource _human = ResourceFactory.createResource(NS + "_human");
	public static final Resource _hybrid = ResourceFactory.createResource(NS + "_hybrid");
	public static final Resource _hybridType = ResourceFactory.createResource(NS + "_hybridType");
	public static final Resource _id = ResourceFactory.createResource(NS + "_id");
	public static final Resource _identified = ResourceFactory.createResource(NS + "_identified");
	public static final Resource _identifier = ResourceFactory.createResource(NS + "_identifier");
	public static final Resource _identity = ResourceFactory.createResource(NS + "_identity");
	public static final Resource _identityCertificate = ResourceFactory.createResource(NS + "_identityCertificate");
	public static final Resource _ifMatch = ResourceFactory.createResource(NS + "_ifMatch");
	public static final Resource _ifModifiedSince = ResourceFactory.createResource(NS + "_ifModifiedSince");
	public static final Resource _ifNoneExist = ResourceFactory.createResource(NS + "_ifNoneExist");
	public static final Resource _ifNoneMatch = ResourceFactory.createResource(NS + "_ifNoneMatch");
	public static final Resource _image = ResourceFactory.createResource(NS + "_image");
	public static final Resource _imageRegion2D = ResourceFactory.createResource(NS + "_imageRegion2D");
	public static final Resource _imageRegion3D = ResourceFactory.createResource(NS + "_imageRegion3D");
	public static final Resource _immunizationEvent = ResourceFactory.createResource(NS + "_immunizationEvent");
	public static final Resource _immutable = ResourceFactory.createResource(NS + "_immutable");
	public static final Resource _implementation = ResourceFactory.createResource(NS + "_implementation");
	public static final Resource _implementationGuide = ResourceFactory.createResource(NS + "_implementationGuide");
	public static final Resource _implicated = ResourceFactory.createResource(NS + "_implicated");
	public static final Resource _implicitRules = ResourceFactory.createResource(NS + "_implicitRules");
	public static final Resource _import = ResourceFactory.createResource(NS + "_import");
	public static final Resource _imports = ResourceFactory.createResource(NS + "_imports");
	public static final Resource _improvementNotation = ResourceFactory.createResource(NS + "_improvementNotation");
	public static final Resource _impurity = ResourceFactory.createResource(NS + "_impurity");
	public static final Resource _inResponseTo = ResourceFactory.createResource(NS + "_inResponseTo");
	public static final Resource _inactive = ResourceFactory.createResource(NS + "_inactive");
	public static final Resource _incidence = ResourceFactory.createResource(NS + "_incidence");
	public static final Resource _include = ResourceFactory.createResource(NS + "_include");
	public static final Resource _includedStructure = ResourceFactory.createResource(NS + "_includedStructure");
	public static final Resource _inclusionCriteria = ResourceFactory.createResource(NS + "_inclusionCriteria");
	public static final Resource _incomplete = ResourceFactory.createResource(NS + "_incomplete");
	public static final Resource _index = ResourceFactory.createResource(NS + "_index");
	public static final Resource _indication = ResourceFactory.createResource(NS + "_indication");
	public static final Resource _indicationGuideline = ResourceFactory.createResource(NS + "_indicationGuideline");
	public static final Resource _inforce = ResourceFactory.createResource(NS + "_inforce");
	public static final Resource _information = ResourceFactory.createResource(NS + "_information");
	public static final Resource _informationProvider = ResourceFactory.createResource(NS + "_informationProvider");
	public static final Resource _informationSequence = ResourceFactory.createResource(NS + "_informationSequence");
	public static final Resource _informationSource = ResourceFactory.createResource(NS + "_informationSource");
	public static final Resource _informationType = ResourceFactory.createResource(NS + "_informationType");
	public static final Resource _ingredient = ResourceFactory.createResource(NS + "_ingredient");
	public static final Resource _ingredientLabel = ResourceFactory.createResource(NS + "_ingredientLabel");
	public static final Resource _initial = ResourceFactory.createResource(NS + "_initial");
	public static final Resource _initialFill = ResourceFactory.createResource(NS + "_initialFill");
	public static final Resource _initialSelected = ResourceFactory.createResource(NS + "_initialSelected");
	public static final Resource _initiator = ResourceFactory.createResource(NS + "_initiator");
	public static final Resource _initiatorActive = ResourceFactory.createResource(NS + "_initiatorActive");
	public static final Resource _input = ResourceFactory.createResource(NS + "_input");
	public static final Resource _inputParameters = ResourceFactory.createResource(NS + "_inputParameters");
	public static final Resource _inputPopulationId = ResourceFactory.createResource(NS + "_inputPopulationId");
	public static final Resource _inputProfile = ResourceFactory.createResource(NS + "_inputProfile");
	public static final Resource _installDate = ResourceFactory.createResource(NS + "_installDate");
	public static final Resource _instance = ResourceFactory.createResource(NS + "_instance");
	public static final Resource _instanceReference = ResourceFactory.createResource(NS + "_instanceReference");
	public static final Resource _instances = ResourceFactory.createResource(NS + "_instances");
	public static final Resource _instantiates = ResourceFactory.createResource(NS + "_instantiates");
	public static final Resource _instantiatesCanonical = ResourceFactory.createResource(NS + "_instantiatesCanonical");
	public static final Resource _instantiatesUri = ResourceFactory.createResource(NS + "_instantiatesUri");
	public static final Resource _instruction = ResourceFactory.createResource(NS + "_instruction");
	public static final Resource _insurance = ResourceFactory.createResource(NS + "_insurance");
	public static final Resource _insurancePlan = ResourceFactory.createResource(NS + "_insurancePlan");
	public static final Resource _insurer = ResourceFactory.createResource(NS + "_insurer");
	public static final Resource _intended = ResourceFactory.createResource(NS + "_intended");
	public static final Resource _intendedEffect = ResourceFactory.createResource(NS + "_intendedEffect");
	public static final Resource _intendedExposure = ResourceFactory.createResource(NS + "_intendedExposure");
	public static final Resource _intendedJurisdiction = ResourceFactory.createResource(NS + "_intendedJurisdiction");
	public static final Resource _intendedRoute = ResourceFactory.createResource(NS + "_intendedRoute");
	public static final Resource _intendedUse = ResourceFactory.createResource(NS + "_intendedUse");
	public static final Resource _intent = ResourceFactory.createResource(NS + "_intent");
	public static final Resource _interactant = ResourceFactory.createResource(NS + "_interactant");
	public static final Resource _interaction = ResourceFactory.createResource(NS + "_interaction");
	public static final Resource _interpretation = ResourceFactory.createResource(NS + "_interpretation");
	public static final Resource _interpreter = ResourceFactory.createResource(NS + "_interpreter");
	public static final Resource _interval = ResourceFactory.createResource(NS + "_interval");
	public static final Resource _intervalUnit = ResourceFactory.createResource(NS + "_intervalUnit");
	public static final Resource _intraspecificDescription = ResourceFactory
			.createResource(NS + "_intraspecificDescription");
	public static final Resource _intraspecificType = ResourceFactory.createResource(NS + "_intraspecificType");
	public static final Resource _inventoryListing = ResourceFactory.createResource(NS + "_inventoryListing");
	public static final Resource _inventoryStatus = ResourceFactory.createResource(NS + "_inventoryStatus");
	public static final Resource _isActive = ResourceFactory.createResource(NS + "_isActive");
	public static final Resource _isDefault = ResourceFactory.createResource(NS + "_isDefault");
	public static final Resource _isDefining = ResourceFactory.createResource(NS + "_isDefining");
	public static final Resource _isDerived = ResourceFactory.createResource(NS + "_isDerived");
	public static final Resource _isExample = ResourceFactory.createResource(NS + "_isExample");
	public static final Resource _isModifier = ResourceFactory.createResource(NS + "_isModifier");
	public static final Resource _isModifierReason = ResourceFactory.createResource(NS + "_isModifierReason");
	public static final Resource _isSubPotent = ResourceFactory.createResource(NS + "_isSubPotent");
	public static final Resource _isSubpotent = ResourceFactory.createResource(NS + "_isSubpotent");
	public static final Resource _isSummary = ResourceFactory.createResource(NS + "_isSummary");
	public static final Resource _issue = ResourceFactory.createResource(NS + "_issue");
	public static final Resource _issued = ResourceFactory.createResource(NS + "_issued");
	public static final Resource _issuer = ResourceFactory.createResource(NS + "_issuer");
	public static final Resource _issuerType = ResourceFactory.createResource(NS + "_issuerType");
	public static final Resource _issues = ResourceFactory.createResource(NS + "_issues");
	public static final Resource _item = ResourceFactory.createResource(NS + "_item");
	public static final Resource _itemSequence = ResourceFactory.createResource(NS + "_itemSequence");
	public static final Resource _itemStatus = ResourceFactory.createResource(NS + "_itemStatus");
	public static final Resource _jurisdiction = ResourceFactory.createResource(NS + "_jurisdiction");
	public static final Resource _justification = ResourceFactory.createResource(NS + "_justification");
	public static final Resource _key = ResourceFactory.createResource(NS + "_key");
	public static final Resource _keyword = ResourceFactory.createResource(NS + "_keyword");
	public static final Resource _kind = ResourceFactory.createResource(NS + "_kind");
	public static final Resource _kingdom = ResourceFactory.createResource(NS + "_kingdom");
	public static final Resource _knownAllergen = ResourceFactory.createResource(NS + "_knownAllergen");
	public static final Resource _knownDataCount = ResourceFactory.createResource(NS + "_knownDataCount");
	public static final Resource _label = ResourceFactory.createResource(NS + "_label");
	public static final Resource _landmarkDescription = ResourceFactory.createResource(NS + "_landmarkDescription");
	public static final Resource _language = ResourceFactory.createResource(NS + "_language");
	public static final Resource _languageCode = ResourceFactory.createResource(NS + "_languageCode");
	public static final Resource _lastModified = ResourceFactory.createResource(NS + "_lastModified");
	public static final Resource _lastOccurrence = ResourceFactory.createResource(NS + "_lastOccurrence");
	public static final Resource _lastOccurrenceDate = ResourceFactory.createResource(NS + "_lastOccurrenceDate");
	public static final Resource _lastPage = ResourceFactory.createResource(NS + "_lastPage");
	public static final Resource _lastPerformed = ResourceFactory.createResource(NS + "_lastPerformed");
	public static final Resource _lastReviewDate = ResourceFactory.createResource(NS + "_lastReviewDate");
	public static final Resource _lastRevisionDate = ResourceFactory.createResource(NS + "_lastRevisionDate");
	public static final Resource _lastUpdated = ResourceFactory.createResource(NS + "_lastUpdated");
	public static final Resource _laterality = ResourceFactory.createResource(NS + "_laterality");
	public static final Resource _latitude = ResourceFactory.createResource(NS + "_latitude");
	public static final Resource _legal = ResourceFactory.createResource(NS + "_legal");
	public static final Resource _legalState = ResourceFactory.createResource(NS + "_legalState");
	public static final Resource _legalStatusOfSupply = ResourceFactory.createResource(NS + "_legalStatusOfSupply");
	public static final Resource _legallyBinding = ResourceFactory.createResource(NS + "_legallyBinding");
	public static final Resource _length = ResourceFactory.createResource(NS + "_length");
	public static final Resource _lensSpecification = ResourceFactory.createResource(NS + "_lensSpecification");
	public static final Resource _level = ResourceFactory.createResource(NS + "_level");
	public static final Resource _library = ResourceFactory.createResource(NS + "_library");
	public static final Resource _license = ResourceFactory.createResource(NS + "_license");
	public static final Resource _lifecycleStatus = ResourceFactory.createResource(NS + "_lifecycleStatus");
	public static final Resource _limit = ResourceFactory.createResource(NS + "_limit");
	public static final Resource _line = ResourceFactory.createResource(NS + "_line");
	public static final Resource _lineItem = ResourceFactory.createResource(NS + "_lineItem");
	public static final Resource _link = ResourceFactory.createResource(NS + "_link");
	public static final Resource _linkId = ResourceFactory.createResource(NS + "_linkId");
	public static final Resource _linkage = ResourceFactory.createResource(NS + "_linkage");
	public static final Resource _listMode = ResourceFactory.createResource(NS + "_listMode");
	public static final Resource _listRuleId = ResourceFactory.createResource(NS + "_listRuleId");
	public static final Resource _literal = ResourceFactory.createResource(NS + "_literal");
	public static final Resource _location = ResourceFactory.createResource(NS + "_location");
	public static final Resource _lockedDate = ResourceFactory.createResource(NS + "_lockedDate");
	public static final Resource _logMessage = ResourceFactory.createResource(NS + "_logMessage");
	public static final Resource _longitude = ResourceFactory.createResource(NS + "_longitude");
	public static final Resource _lotNumber = ResourceFactory.createResource(NS + "_lotNumber");
	public static final Resource _low = ResourceFactory.createResource(NS + "_low");
	public static final Resource _lowNumerator = ResourceFactory.createResource(NS + "_lowNumerator");
	public static final Resource _lowerLimit = ResourceFactory.createResource(NS + "_lowerLimit");
	public static final Resource _management = ResourceFactory.createResource(NS + "_management");
	public static final Resource _manager = ResourceFactory.createResource(NS + "_manager");
	public static final Resource _managingEntity = ResourceFactory.createResource(NS + "_managingEntity");
	public static final Resource _managingOrganization = ResourceFactory.createResource(NS + "_managingOrganization");
	public static final Resource _manifest = ResourceFactory.createResource(NS + "_manifest");
	public static final Resource _manifestation = ResourceFactory.createResource(NS + "_manifestation");
	public static final Resource _manipulated = ResourceFactory.createResource(NS + "_manipulated");
	public static final Resource _manufactureDate = ResourceFactory.createResource(NS + "_manufactureDate");
	public static final Resource _manufacturedDoseForm = ResourceFactory.createResource(NS + "_manufacturedDoseForm");
	public static final Resource _manufacturer = ResourceFactory.createResource(NS + "_manufacturer");
	public static final Resource _map = ResourceFactory.createResource(NS + "_map");
	public static final Resource _mapping = ResourceFactory.createResource(NS + "_mapping");
	public static final Resource _maritalStatus = ResourceFactory.createResource(NS + "_maritalStatus");
	public static final Resource _marketDistribution = ResourceFactory.createResource(NS + "_marketDistribution");
	public static final Resource _marketPeriod = ResourceFactory.createResource(NS + "_marketPeriod");
	public static final Resource _marketingAuthorizationHolder = ResourceFactory
			.createResource(NS + "_marketingAuthorizationHolder");
	public static final Resource _marketingStatus = ResourceFactory.createResource(NS + "_marketingStatus");
	public static final Resource _masterFile = ResourceFactory.createResource(NS + "_masterFile");
	public static final Resource _matchStatus = ResourceFactory.createResource(NS + "_matchStatus");
	public static final Resource _material = ResourceFactory.createResource(NS + "_material");
	public static final Resource _materialType = ResourceFactory.createResource(NS + "_materialType");
	public static final Resource _maternalOrganismId = ResourceFactory.createResource(NS + "_maternalOrganismId");
	public static final Resource _maternalOrganismName = ResourceFactory.createResource(NS + "_maternalOrganismName");
	public static final Resource _max = ResourceFactory.createResource(NS + "_max");
	public static final Resource _maxCount = ResourceFactory.createResource(NS + "_maxCount");
	public static final Resource _maxDispense = ResourceFactory.createResource(NS + "_maxDispense");
	public static final Resource _maxDosePerAdministration = ResourceFactory
			.createResource(NS + "_maxDosePerAdministration");
	public static final Resource _maxDosePerDay = ResourceFactory.createResource(NS + "_maxDosePerDay");
	public static final Resource _maxDosePerLifetime = ResourceFactory.createResource(NS + "_maxDosePerLifetime");
	public static final Resource _maxDosePerPeriod = ResourceFactory.createResource(NS + "_maxDosePerPeriod");
	public static final Resource _maxDosePerTreatmentPeriod = ResourceFactory
			.createResource(NS + "_maxDosePerTreatmentPeriod");
	public static final Resource _maxDuration = ResourceFactory.createResource(NS + "_maxDuration");
	public static final Resource _maxLength = ResourceFactory.createResource(NS + "_maxLength");
	public static final Resource _maxParticipants = ResourceFactory.createResource(NS + "_maxParticipants");
	public static final Resource _maxSingleDose = ResourceFactory.createResource(NS + "_maxSingleDose");
	public static final Resource _maxTreatmentPeriod = ResourceFactory.createResource(NS + "_maxTreatmentPeriod");
	public static final Resource _maxValue = ResourceFactory.createResource(NS + "_maxValue");
	public static final Resource _maxVolumeToDeliver = ResourceFactory.createResource(NS + "_maxVolumeToDeliver");
	public static final Resource _meaning = ResourceFactory.createResource(NS + "_meaning");
	public static final Resource _meaningWhenMissing = ResourceFactory.createResource(NS + "_meaningWhenMissing");
	public static final Resource _measure = ResourceFactory.createResource(NS + "_measure");
	public static final Resource _measureScore = ResourceFactory.createResource(NS + "_measureScore");
	public static final Resource _measurementFrequency = ResourceFactory.createResource(NS + "_measurementFrequency");
	public static final Resource _measurementPoint = ResourceFactory.createResource(NS + "_measurementPoint");
	public static final Resource _measurementType = ResourceFactory.createResource(NS + "_measurementType");
	public static final Resource _media = ResourceFactory.createResource(NS + "_media");
	public static final Resource _medication = ResourceFactory.createResource(NS + "_medication");
	public static final Resource _medicineClassification = ResourceFactory
			.createResource(NS + "_medicineClassification");
	public static final Resource _medium = ResourceFactory.createResource(NS + "_medium");
	public static final Resource _member = ResourceFactory.createResource(NS + "_member");
	public static final Resource _membership = ResourceFactory.createResource(NS + "_membership");
	public static final Resource _message = ResourceFactory.createResource(NS + "_message");
	public static final Resource _messaging = ResourceFactory.createResource(NS + "_messaging");
	public static final Resource _meta = ResourceFactory.createResource(NS + "_meta");
	public static final Resource _metadata = ResourceFactory.createResource(NS + "_metadata");
	public static final Resource _method = ResourceFactory.createResource(NS + "_method");
	public static final Resource _methodType = ResourceFactory.createResource(NS + "_methodType");
	public static final Resource _milestone = ResourceFactory.createResource(NS + "_milestone");
	public static final Resource _mimeType = ResourceFactory.createResource(NS + "_mimeType");
	public static final Resource _min = ResourceFactory.createResource(NS + "_min");
	public static final Resource _minValue = ResourceFactory.createResource(NS + "_minValue");
	public static final Resource _minimumId = ResourceFactory.createResource(NS + "_minimumId");
	public static final Resource _minimumVolume = ResourceFactory.createResource(NS + "_minimumVolume");
	public static final Resource _minutesDuration = ResourceFactory.createResource(NS + "_minutesDuration");
	public static final Resource _mitigatingAction = ResourceFactory.createResource(NS + "_mitigatingAction");
	public static final Resource _mitigation = ResourceFactory.createResource(NS + "_mitigation");
	public static final Resource _modality = ResourceFactory.createResource(NS + "_modality");
	public static final Resource _mode = ResourceFactory.createResource(NS + "_mode");
	public static final Resource _modelCharacteristic = ResourceFactory.createResource(NS + "_modelCharacteristic");
	public static final Resource _modelNumber = ResourceFactory.createResource(NS + "_modelNumber");
	public static final Resource _modification = ResourceFactory.createResource(NS + "_modification");
	public static final Resource _modifier = ResourceFactory.createResource(NS + "_modifier");
	public static final Resource _module = ResourceFactory.createResource(NS + "_module");
	public static final Resource _moiety = ResourceFactory.createResource(NS + "_moiety");
	public static final Resource _molecularFormula = ResourceFactory.createResource(NS + "_molecularFormula");
	public static final Resource _molecularFormulaByMoiety = ResourceFactory
			.createResource(NS + "_molecularFormulaByMoiety");
	public static final Resource _molecularWeight = ResourceFactory.createResource(NS + "_molecularWeight");
	public static final Resource _monday = ResourceFactory.createResource(NS + "_monday");
	public static final Resource _monitoringProgram = ResourceFactory.createResource(NS + "_monitoringProgram");
	public static final Resource _monograph = ResourceFactory.createResource(NS + "_monograph");
	public static final Resource _monomerSet = ResourceFactory.createResource(NS + "_monomerSet");
	public static final Resource _monthInterval = ResourceFactory.createResource(NS + "_monthInterval");
	public static final Resource _monthlyTemplate = ResourceFactory.createResource(NS + "_monthlyTemplate");
	public static final Resource _morphology = ResourceFactory.createResource(NS + "_morphology");
	public static final Resource _multipleAnd = ResourceFactory.createResource(NS + "_multipleAnd");
	public static final Resource _multipleBirth = ResourceFactory.createResource(NS + "_multipleBirth");
	public static final Resource _multipleOr = ResourceFactory.createResource(NS + "_multipleOr");
	public static final Resource _multipleResultsAllowed = ResourceFactory
			.createResource(NS + "_multipleResultsAllowed");
	public static final Resource _mustHaveValue = ResourceFactory.createResource(NS + "_mustHaveValue");
	public static final Resource _mustSupport = ResourceFactory.createResource(NS + "_mustSupport");
	public static final Resource _nTerminalModification = ResourceFactory.createResource(NS + "_nTerminalModification");
	public static final Resource _nTerminalModificationId = ResourceFactory
			.createResource(NS + "_nTerminalModificationId");
	public static final Resource _name = ResourceFactory.createResource(NS + "_name");
	public static final Resource _nameType = ResourceFactory.createResource(NS + "_nameType");
	public static final Resource _narrative = ResourceFactory.createResource(NS + "_narrative");
	public static final Resource _navigationLinks = ResourceFactory.createResource(NS + "_navigationLinks");
	public static final Resource _need = ResourceFactory.createResource(NS + "_need");
	public static final Resource _needsMap = ResourceFactory.createResource(NS + "_needsMap");
	public static final Resource _net = ResourceFactory.createResource(NS + "_net");
	public static final Resource _netContent = ResourceFactory.createResource(NS + "_netContent");
	public static final Resource _network = ResourceFactory.createResource(NS + "_network");
	public static final Resource _next = ResourceFactory.createResource(NS + "_next");
	public static final Resource _nextScheduled = ResourceFactory.createResource(NS + "_nextScheduled");
	public static final Resource _noMap = ResourceFactory.createResource(NS + "_noMap");
	public static final Resource _node = ResourceFactory.createResource(NS + "_node");
	public static final Resource _nodeId = ResourceFactory.createResource(NS + "_nodeId");
	public static final Resource _normalCodedValueSet = ResourceFactory.createResource(NS + "_normalCodedValueSet");
	public static final Resource _normalValue = ResourceFactory.createResource(NS + "_normalValue");
	public static final Resource _notAvailableTime = ResourceFactory.createResource(NS + "_notAvailableTime");
	public static final Resource _notConsumed = ResourceFactory.createResource(NS + "_notConsumed");
	public static final Resource _notConsumedReason = ResourceFactory.createResource(NS + "_notConsumedReason");
	public static final Resource _notPerformedReason = ResourceFactory.createResource(NS + "_notPerformedReason");
	public static final Resource _note = ResourceFactory.createResource(NS + "_note");
	public static final Resource _noteNumber = ResourceFactory.createResource(NS + "_noteNumber");
	public static final Resource _notificationEvent = ResourceFactory.createResource(NS + "_notificationEvent");
	public static final Resource _notificationShape = ResourceFactory.createResource(NS + "_notificationShape");
	public static final Resource _nthWeekOfMonth = ResourceFactory.createResource(NS + "_nthWeekOfMonth");
	public static final Resource _nucleicAcid = ResourceFactory.createResource(NS + "_nucleicAcid");
	public static final Resource _number = ResourceFactory.createResource(NS + "_number");
	public static final Resource _numberAffected = ResourceFactory.createResource(NS + "_numberAffected");
	public static final Resource _numberOfEvents = ResourceFactory.createResource(NS + "_numberOfEvents");
	public static final Resource _numberOfInstances = ResourceFactory.createResource(NS + "_numberOfInstances");
	public static final Resource _numberOfParticipants = ResourceFactory.createResource(NS + "_numberOfParticipants");
	public static final Resource _numberOfRepeatsAllowed = ResourceFactory
			.createResource(NS + "_numberOfRepeatsAllowed");
	public static final Resource _numberOfSeries = ResourceFactory.createResource(NS + "_numberOfSeries");
	public static final Resource _numberOfStudies = ResourceFactory.createResource(NS + "_numberOfStudies");
	public static final Resource _numberOfSubunits = ResourceFactory.createResource(NS + "_numberOfSubunits");
	public static final Resource _numerator = ResourceFactory.createResource(NS + "_numerator");
	public static final Resource _nutrient = ResourceFactory.createResource(NS + "_nutrient");
	public static final Resource _nutritionProduct = ResourceFactory.createResource(NS + "_nutritionProduct");
	public static final Resource _object = ResourceFactory.createResource(NS + "_object");
	public static final Resource _objective = ResourceFactory.createResource(NS + "_objective");
	public static final Resource _observation = ResourceFactory.createResource(NS + "_observation");
	public static final Resource _observationRequirement = ResourceFactory
			.createResource(NS + "_observationRequirement");
	public static final Resource _observationResultRequirement = ResourceFactory
			.createResource(NS + "_observationResultRequirement");
	public static final Resource _observed = ResourceFactory.createResource(NS + "_observed");
	public static final Resource _observedGroup = ResourceFactory.createResource(NS + "_observedGroup");
	public static final Resource _observer = ResourceFactory.createResource(NS + "_observer");
	public static final Resource _occurence = ResourceFactory.createResource(NS + "_occurence");
	public static final Resource _occurred = ResourceFactory.createResource(NS + "_occurred");
	public static final Resource _occurrence = ResourceFactory.createResource(NS + "_occurrence");
	public static final Resource _occurrenceChanged = ResourceFactory.createResource(NS + "_occurrenceChanged");
	public static final Resource _occurrenceCount = ResourceFactory.createResource(NS + "_occurrenceCount");
	public static final Resource _occurrenceDate = ResourceFactory.createResource(NS + "_occurrenceDate");
	public static final Resource _occurrenceDateTime = ResourceFactory.createResource(NS + "_occurrenceDateTime");
	public static final Resource _offer = ResourceFactory.createResource(NS + "_offer");
	public static final Resource _offeredIn = ResourceFactory.createResource(NS + "_offeredIn");
	public static final Resource _official = ResourceFactory.createResource(NS + "_official");
	public static final Resource _offset = ResourceFactory.createResource(NS + "_offset");
	public static final Resource _offsets = ResourceFactory.createResource(NS + "_offsets");
	public static final Resource _oligoNucleotideType = ResourceFactory.createResource(NS + "_oligoNucleotideType");
	public static final Resource _onAdmission = ResourceFactory.createResource(NS + "_onAdmission");
	public static final Resource _onBehalfOf = ResourceFactory.createResource(NS + "_onBehalfOf");
	public static final Resource _onHold = ResourceFactory.createResource(NS + "_onHold");
	public static final Resource _onset = ResourceFactory.createResource(NS + "_onset");
	public static final Resource _op = ResourceFactory.createResource(NS + "_op");
	public static final Resource _operation = ResourceFactory.createResource(NS + "_operation");
	public static final Resource _operationType = ResourceFactory.createResource(NS + "_operationType");
	public static final Resource _operationTypeReason = ResourceFactory.createResource(NS + "_operationTypeReason");
	public static final Resource _operationalStatus = ResourceFactory.createResource(NS + "_operationalStatus");
	public static final Resource _operator = ResourceFactory.createResource(NS + "_operator");
	public static final Resource _opticalActivity = ResourceFactory.createResource(NS + "_opticalActivity");
	public static final Resource _option = ResourceFactory.createResource(NS + "_option");
	public static final Resource _oralDiet = ResourceFactory.createResource(NS + "_oralDiet");
	public static final Resource _order = ResourceFactory.createResource(NS + "_order");
	public static final Resource _orderDetail = ResourceFactory.createResource(NS + "_orderDetail");
	public static final Resource _orderMeaning = ResourceFactory.createResource(NS + "_orderMeaning");
	public static final Resource _ordered = ResourceFactory.createResource(NS + "_ordered");
	public static final Resource _orderedBy = ResourceFactory.createResource(NS + "_orderedBy");
	public static final Resource _orderer = ResourceFactory.createResource(NS + "_orderer");
	public static final Resource _ordinalPosition = ResourceFactory.createResource(NS + "_ordinalPosition");
	public static final Resource _organism = ResourceFactory.createResource(NS + "_organism");
	public static final Resource _organismGeneral = ResourceFactory.createResource(NS + "_organismGeneral");
	public static final Resource _organismId = ResourceFactory.createResource(NS + "_organismId");
	public static final Resource _organismName = ResourceFactory.createResource(NS + "_organismName");
	public static final Resource _organismType = ResourceFactory.createResource(NS + "_organismType");
	public static final Resource _organization = ResourceFactory.createResource(NS + "_organization");
	public static final Resource _organizationReference = ResourceFactory.createResource(NS + "_organizationReference");
	public static final Resource _orientation = ResourceFactory.createResource(NS + "_orientation");
	public static final Resource _origin = ResourceFactory.createResource(NS + "_origin");
	public static final Resource _originRelationshipType = ResourceFactory
			.createResource(NS + "_originRelationshipType");
	public static final Resource _originalPrescription = ResourceFactory.createResource(NS + "_originalPrescription");
	public static final Resource _originatingAppointment = ResourceFactory
			.createResource(NS + "_originatingAppointment");
	public static final Resource _other = ResourceFactory.createResource(NS + "_other");
	public static final Resource _otherMap = ResourceFactory.createResource(NS + "_otherMap");
	public static final Resource _otherTherapy = ResourceFactory.createResource(NS + "_otherTherapy");
	public static final Resource _outcome = ResourceFactory.createResource(NS + "_outcome");
	public static final Resource _outcomeMeasure = ResourceFactory.createResource(NS + "_outcomeMeasure");
	public static final Resource _output = ResourceFactory.createResource(NS + "_output");
	public static final Resource _outputParameters = ResourceFactory.createResource(NS + "_outputParameters");
	public static final Resource _outputProfile = ResourceFactory.createResource(NS + "_outputProfile");
	public static final Resource _outsideFoodAllowed = ResourceFactory.createResource(NS + "_outsideFoodAllowed");
	public static final Resource _overbooked = ResourceFactory.createResource(NS + "_overbooked");
	public static final Resource _overload = ResourceFactory.createResource(NS + "_overload");
	public static final Resource _overrideReason = ResourceFactory.createResource(NS + "_overrideReason");
	public static final Resource _ownedBy = ResourceFactory.createResource(NS + "_ownedBy");
	public static final Resource _owner = ResourceFactory.createResource(NS + "_owner");
	public static final Resource _packageCode = ResourceFactory.createResource(NS + "_packageCode");
	public static final Resource _packageFor = ResourceFactory.createResource(NS + "_packageFor");
	public static final Resource _packageId = ResourceFactory.createResource(NS + "_packageId");
	public static final Resource _packagedMedicinalProduct = ResourceFactory
			.createResource(NS + "_packagedMedicinalProduct");
	public static final Resource _packagedProduct = ResourceFactory.createResource(NS + "_packagedProduct");
	public static final Resource _packaging = ResourceFactory.createResource(NS + "_packaging");
	public static final Resource _page = ResourceFactory.createResource(NS + "_page");
	public static final Resource _pageCount = ResourceFactory.createResource(NS + "_pageCount");
	public static final Resource _pageString = ResourceFactory.createResource(NS + "_pageString");
	public static final Resource _pages = ResourceFactory.createResource(NS + "_pages");
	public static final Resource _paging = ResourceFactory.createResource(NS + "_paging");
	public static final Resource _param = ResourceFactory.createResource(NS + "_param");
	public static final Resource _parameter = ResourceFactory.createResource(NS + "_parameter");
	public static final Resource _parameterFocus = ResourceFactory.createResource(NS + "_parameterFocus");
	public static final Resource _parameterName = ResourceFactory.createResource(NS + "_parameterName");
	public static final Resource _params = ResourceFactory.createResource(NS + "_params");
	public static final Resource _parent = ResourceFactory.createResource(NS + "_parent");
	public static final Resource _parentSubstanceId = ResourceFactory.createResource(NS + "_parentSubstanceId");
	public static final Resource _parentSubstanceName = ResourceFactory.createResource(NS + "_parentSubstanceName");
	public static final Resource _part = ResourceFactory.createResource(NS + "_part");
	public static final Resource _partDescription = ResourceFactory.createResource(NS + "_partDescription");
	public static final Resource _partLocation = ResourceFactory.createResource(NS + "_partLocation");
	public static final Resource _partNumber = ResourceFactory.createResource(NS + "_partNumber");
	public static final Resource _partOf = ResourceFactory.createResource(NS + "_partOf");
	public static final Resource _participant = ResourceFactory.createResource(NS + "_participant");
	public static final Resource _participantStatus = ResourceFactory.createResource(NS + "_participantStatus");
	public static final Resource _participantType = ResourceFactory.createResource(NS + "_participantType");
	public static final Resource _participatingOrganization = ResourceFactory
			.createResource(NS + "_participatingOrganization");
	public static final Resource _party = ResourceFactory.createResource(NS + "_party");
	public static final Resource _patchFormat = ResourceFactory.createResource(NS + "_patchFormat");
	public static final Resource _paternalOrganismId = ResourceFactory.createResource(NS + "_paternalOrganismId");
	public static final Resource _paternalOrganismName = ResourceFactory.createResource(NS + "_paternalOrganismName");
	public static final Resource _path = ResourceFactory.createResource(NS + "_path");
	public static final Resource _patient = ResourceFactory.createResource(NS + "_patient");
	public static final Resource _patientCharacteristic = ResourceFactory.createResource(NS + "_patientCharacteristic");
	public static final Resource _patientInstruction = ResourceFactory.createResource(NS + "_patientInstruction");
	public static final Resource _patientPaid = ResourceFactory.createResource(NS + "_patientPaid");
	public static final Resource _patientPreparation = ResourceFactory.createResource(NS + "_patientPreparation");
	public static final Resource _pattern = ResourceFactory.createResource(NS + "_pattern");
	public static final Resource _pause = ResourceFactory.createResource(NS + "_pause");
	public static final Resource _payee = ResourceFactory.createResource(NS + "_payee");
	public static final Resource _payeeType = ResourceFactory.createResource(NS + "_payeeType");
	public static final Resource _payload = ResourceFactory.createResource(NS + "_payload");
	public static final Resource _payment = ResourceFactory.createResource(NS + "_payment");
	public static final Resource _paymentBy = ResourceFactory.createResource(NS + "_paymentBy");
	public static final Resource _paymentDate = ResourceFactory.createResource(NS + "_paymentDate");
	public static final Resource _paymentIdentifier = ResourceFactory.createResource(NS + "_paymentIdentifier");
	public static final Resource _paymentIssuer = ResourceFactory.createResource(NS + "_paymentIssuer");
	public static final Resource _paymentStatus = ResourceFactory.createResource(NS + "_paymentStatus");
	public static final Resource _paymentTerms = ResourceFactory.createResource(NS + "_paymentTerms");
	public static final Resource _pediatricUseIndicator = ResourceFactory.createResource(NS + "_pediatricUseIndicator");
	public static final Resource _performed = ResourceFactory.createResource(NS + "_performed");
	public static final Resource _performedActivity = ResourceFactory.createResource(NS + "_performedActivity");
	public static final Resource _performer = ResourceFactory.createResource(NS + "_performer");
	public static final Resource _performerLinkId = ResourceFactory.createResource(NS + "_performerLinkId");
	public static final Resource _performerRole = ResourceFactory.createResource(NS + "_performerRole");
	public static final Resource _performerType = ResourceFactory.createResource(NS + "_performerType");
	public static final Resource _performingOrganization = ResourceFactory
			.createResource(NS + "_performingOrganization");
	public static final Resource _period = ResourceFactory.createResource(NS + "_period");
	public static final Resource _periodMax = ResourceFactory.createResource(NS + "_periodMax");
	public static final Resource _periodType = ResourceFactory.createResource(NS + "_periodType");
	public static final Resource _periodUnit = ResourceFactory.createResource(NS + "_periodUnit");
	public static final Resource _permittedDataType = ResourceFactory.createResource(NS + "_permittedDataType");
	public static final Resource _permittedUnit = ResourceFactory.createResource(NS + "_permittedUnit");
	public static final Resource _phase = ResourceFactory.createResource(NS + "_phase");
	public static final Resource _photo = ResourceFactory.createResource(NS + "_photo");
	public static final Resource _phylum = ResourceFactory.createResource(NS + "_phylum");
	public static final Resource _plan = ResourceFactory.createResource(NS + "_plan");
	public static final Resource _plannedActivityReference = ResourceFactory
			.createResource(NS + "_plannedActivityReference");
	public static final Resource _plannedEndDate = ResourceFactory.createResource(NS + "_plannedEndDate");
	public static final Resource _plannedStartDate = ResourceFactory.createResource(NS + "_plannedStartDate");
	public static final Resource _planningHorizon = ResourceFactory.createResource(NS + "_planningHorizon");
	public static final Resource _points = ResourceFactory.createResource(NS + "_points");
	public static final Resource _policy = ResourceFactory.createResource(NS + "_policy");
	public static final Resource _policyBasis = ResourceFactory.createResource(NS + "_policyBasis");
	public static final Resource _policyHolder = ResourceFactory.createResource(NS + "_policyHolder");
	public static final Resource _policyText = ResourceFactory.createResource(NS + "_policyText");
	public static final Resource _polymer = ResourceFactory.createResource(NS + "_polymer");
	public static final Resource _population = ResourceFactory.createResource(NS + "_population");
	public static final Resource _position = ResourceFactory.createResource(NS + "_position");
	public static final Resource _postConditions = ResourceFactory.createResource(NS + "_postConditions");
	public static final Resource _postalCode = ResourceFactory.createResource(NS + "_postalCode");
	public static final Resource _power = ResourceFactory.createResource(NS + "_power");
	public static final Resource _practiceSetting = ResourceFactory.createResource(NS + "_practiceSetting");
	public static final Resource _practitioner = ResourceFactory.createResource(NS + "_practitioner");
	public static final Resource _preAdmissionIdentifier = ResourceFactory
			.createResource(NS + "_preAdmissionIdentifier");
	public static final Resource _preAuthPeriod = ResourceFactory.createResource(NS + "_preAuthPeriod");
	public static final Resource _preAuthRef = ResourceFactory.createResource(NS + "_preAuthRef");
	public static final Resource _preAuthRefPeriod = ResourceFactory.createResource(NS + "_preAuthRefPeriod");
	public static final Resource _preConditions = ResourceFactory.createResource(NS + "_preConditions");
	public static final Resource _precedence = ResourceFactory.createResource(NS + "_precedence");
	public static final Resource _precheckBehavior = ResourceFactory.createResource(NS + "_precheckBehavior");
	public static final Resource _precondition = ResourceFactory.createResource(NS + "_precondition");
	public static final Resource _predecessor = ResourceFactory.createResource(NS + "_predecessor");
	public static final Resource _prediction = ResourceFactory.createResource(NS + "_prediction");
	public static final Resource _preference = ResourceFactory.createResource(NS + "_preference");
	public static final Resource _preferred = ResourceFactory.createResource(NS + "_preferred");
	public static final Resource _preferredReportName = ResourceFactory.createResource(NS + "_preferredReportName");
	public static final Resource _prefix = ResourceFactory.createResource(NS + "_prefix");
	public static final Resource _preparation = ResourceFactory.createResource(NS + "_preparation");
	public static final Resource _preparationInstruction = ResourceFactory
			.createResource(NS + "_preparationInstruction");
	public static final Resource _preparedDate = ResourceFactory.createResource(NS + "_preparedDate");
	public static final Resource _prescriber = ResourceFactory.createResource(NS + "_prescriber");
	public static final Resource _prescription = ResourceFactory.createResource(NS + "_prescription");
	public static final Resource _presentation = ResourceFactory.createResource(NS + "_presentation");
	public static final Resource _presentedForm = ResourceFactory.createResource(NS + "_presentedForm");
	public static final Resource _preventiveAction = ResourceFactory.createResource(NS + "_preventiveAction");
	public static final Resource _previous = ResourceFactory.createResource(NS + "_previous");
	public static final Resource _previousAppointment = ResourceFactory.createResource(NS + "_previousAppointment");
	public static final Resource _priceComponent = ResourceFactory.createResource(NS + "_priceComponent");
	public static final Resource _primaryPurposeType = ResourceFactory.createResource(NS + "_primaryPurposeType");
	public static final Resource _primarySource = ResourceFactory.createResource(NS + "_primarySource");
	public static final Resource _priorPrescription = ResourceFactory.createResource(NS + "_priorPrescription");
	public static final Resource _priority = ResourceFactory.createResource(NS + "_priority");
	public static final Resource _prism = ResourceFactory.createResource(NS + "_prism");
	public static final Resource _probability = ResourceFactory.createResource(NS + "_probability");
	public static final Resource _problem = ResourceFactory.createResource(NS + "_problem");
	public static final Resource _procedure = ResourceFactory.createResource(NS + "_procedure");
	public static final Resource _procedureSequence = ResourceFactory.createResource(NS + "_procedureSequence");
	public static final Resource _process = ResourceFactory.createResource(NS + "_process");
	public static final Resource _processNote = ResourceFactory.createResource(NS + "_processNote");
	public static final Resource _processing = ResourceFactory.createResource(NS + "_processing");
	public static final Resource _processingFacility = ResourceFactory.createResource(NS + "_processingFacility");
	public static final Resource _processingMode = ResourceFactory.createResource(NS + "_processingMode");
	public static final Resource _processor = ResourceFactory.createResource(NS + "_processor");
	public static final Resource _producedFrom = ResourceFactory.createResource(NS + "_producedFrom");
	public static final Resource _product = ResourceFactory.createResource(NS + "_product");
	public static final Resource _productCategory = ResourceFactory.createResource(NS + "_productCategory");
	public static final Resource _productCode = ResourceFactory.createResource(NS + "_productCode");
	public static final Resource _productName = ResourceFactory.createResource(NS + "_productName");
	public static final Resource _productOrService = ResourceFactory.createResource(NS + "_productOrService");
	public static final Resource _productOrServiceEnd = ResourceFactory.createResource(NS + "_productOrServiceEnd");
	public static final Resource _productReference = ResourceFactory.createResource(NS + "_productReference");
	public static final Resource _productStatus = ResourceFactory.createResource(NS + "_productStatus");
	public static final Resource _productType = ResourceFactory.createResource(NS + "_productType");
	public static final Resource _productionIdentifierInUDI = ResourceFactory
			.createResource(NS + "_productionIdentifierInUDI");
	public static final Resource _profile = ResourceFactory.createResource(NS + "_profile");
	public static final Resource _prognosisCodeableConcept = ResourceFactory
			.createResource(NS + "_prognosisCodeableConcept");
	public static final Resource _prognosisReference = ResourceFactory.createResource(NS + "_prognosisReference");
	public static final Resource _program = ResourceFactory.createResource(NS + "_program");
	public static final Resource _programCode = ResourceFactory.createResource(NS + "_programCode");
	public static final Resource _programEligibility = ResourceFactory.createResource(NS + "_programEligibility");
	public static final Resource _programStatus = ResourceFactory.createResource(NS + "_programStatus");
	public static final Resource _progress = ResourceFactory.createResource(NS + "_progress");
	public static final Resource _progressStatus = ResourceFactory.createResource(NS + "_progressStatus");
	public static final Resource _property = ResourceFactory.createResource(NS + "_property");
	public static final Resource _propertyGroup = ResourceFactory.createResource(NS + "_propertyGroup");
	public static final Resource _proposedNewTime = ResourceFactory.createResource(NS + "_proposedNewTime");
	public static final Resource _protein = ResourceFactory.createResource(NS + "_protein");
	public static final Resource _protocol = ResourceFactory.createResource(NS + "_protocol");
	public static final Resource _protocolApplied = ResourceFactory.createResource(NS + "_protocolApplied");
	public static final Resource _protocolPerformed = ResourceFactory.createResource(NS + "_protocolPerformed");
	public static final Resource _providedBy = ResourceFactory.createResource(NS + "_providedBy");
	public static final Resource _provider = ResourceFactory.createResource(NS + "_provider");
	public static final Resource _provision = ResourceFactory.createResource(NS + "_provision");
	public static final Resource _proxyIdentityCertificate = ResourceFactory
			.createResource(NS + "_proxyIdentityCertificate");
	public static final Resource _proxySignature = ResourceFactory.createResource(NS + "_proxySignature");
	public static final Resource _publicationDate = ResourceFactory.createResource(NS + "_publicationDate");
	public static final Resource _publicationDateSeason = ResourceFactory.createResource(NS + "_publicationDateSeason");
	public static final Resource _publicationDateText = ResourceFactory.createResource(NS + "_publicationDateText");
	public static final Resource _publicationForm = ResourceFactory.createResource(NS + "_publicationForm");
	public static final Resource _publicationStatus = ResourceFactory.createResource(NS + "_publicationStatus");
	public static final Resource _publishedIn = ResourceFactory.createResource(NS + "_publishedIn");
	public static final Resource _publisher = ResourceFactory.createResource(NS + "_publisher");
	public static final Resource _publisherLocation = ResourceFactory.createResource(NS + "_publisherLocation");
	public static final Resource _purpose = ResourceFactory.createResource(NS + "_purpose");
	public static final Resource _pushTypeAvailable = ResourceFactory.createResource(NS + "_pushTypeAvailable");
	public static final Resource _qualification = ResourceFactory.createResource(NS + "_qualification");
	public static final Resource _qualifiedValue = ResourceFactory.createResource(NS + "_qualifiedValue");
	public static final Resource _qualifier = ResourceFactory.createResource(NS + "_qualifier");
	public static final Resource _qualifiers = ResourceFactory.createResource(NS + "_qualifiers");
	public static final Resource _qualitativeRisk = ResourceFactory.createResource(NS + "_qualitativeRisk");
	public static final Resource _quantity = ResourceFactory.createResource(NS + "_quantity");
	public static final Resource _query = ResourceFactory.createResource(NS + "_query");
	public static final Resource _queryCriteria = ResourceFactory.createResource(NS + "_queryCriteria");
	public static final Resource _question = ResourceFactory.createResource(NS + "_question");
	public static final Resource _questionnaire = ResourceFactory.createResource(NS + "_questionnaire");
	public static final Resource _range = ResourceFactory.createResource(NS + "_range");
	public static final Resource _rangeCategory = ResourceFactory.createResource(NS + "_rangeCategory");
	public static final Resource _rank = ResourceFactory.createResource(NS + "_rank");
	public static final Resource _rankingOrder = ResourceFactory.createResource(NS + "_rankingOrder");
	public static final Resource _rate = ResourceFactory.createResource(NS + "_rate");
	public static final Resource _rateAggregation = ResourceFactory.createResource(NS + "_rateAggregation");
	public static final Resource _rater = ResourceFactory.createResource(NS + "_rater");
	public static final Resource _rating = ResourceFactory.createResource(NS + "_rating");
	public static final Resource _ratioHighLimitAmount = ResourceFactory.createResource(NS + "_ratioHighLimitAmount");
	public static final Resource _ratioType = ResourceFactory.createResource(NS + "_ratioType");
	public static final Resource _rationale = ResourceFactory.createResource(NS + "_rationale");
	public static final Resource _reAdmission = ResourceFactory.createResource(NS + "_reAdmission");
	public static final Resource _reaction = ResourceFactory.createResource(NS + "_reaction");
	public static final Resource _readHistory = ResourceFactory.createResource(NS + "_readHistory");
	public static final Resource _readOnly = ResourceFactory.createResource(NS + "_readOnly");
	public static final Resource _reason = ResourceFactory.createResource(NS + "_reason");
	public static final Resource _reasonLinkId = ResourceFactory.createResource(NS + "_reasonLinkId");
	public static final Resource _recall = ResourceFactory.createResource(NS + "_recall");
	public static final Resource _received = ResourceFactory.createResource(NS + "_received");
	public static final Resource _receivedTime = ResourceFactory.createResource(NS + "_receivedTime");
	public static final Resource _receiver = ResourceFactory.createResource(NS + "_receiver");
	public static final Resource _receiverActive = ResourceFactory.createResource(NS + "_receiverActive");
	public static final Resource _recipient = ResourceFactory.createResource(NS + "_recipient");
	public static final Resource _recommendation = ResourceFactory.createResource(NS + "_recommendation");
	public static final Resource _recorded = ResourceFactory.createResource(NS + "_recorded");
	public static final Resource _recordedDate = ResourceFactory.createResource(NS + "_recordedDate");
	public static final Resource _recorder = ResourceFactory.createResource(NS + "_recorder");
	public static final Resource _recruitment = ResourceFactory.createResource(NS + "_recruitment");
	public static final Resource _recurrenceId = ResourceFactory.createResource(NS + "_recurrenceId");
	public static final Resource _recurrenceTemplate = ResourceFactory.createResource(NS + "_recurrenceTemplate");
	public static final Resource _recurrenceType = ResourceFactory.createResource(NS + "_recurrenceType");
	public static final Resource _recurring = ResourceFactory.createResource(NS + "_recurring");
	public static final Resource _reference = ResourceFactory.createResource(NS + "_reference");
	public static final Resource _referenceInformation = ResourceFactory.createResource(NS + "_referenceInformation");
	public static final Resource _referenceNumber = ResourceFactory.createResource(NS + "_referenceNumber");
	public static final Resource _referencePolicy = ResourceFactory.createResource(NS + "_referencePolicy");
	public static final Resource _referenceRange = ResourceFactory.createResource(NS + "_referenceRange");
	public static final Resource _referenceStrength = ResourceFactory.createResource(NS + "_referenceStrength");
	public static final Resource _referencedFrom = ResourceFactory.createResource(NS + "_referencedFrom");
	public static final Resource _referral = ResourceFactory.createResource(NS + "_referral");
	public static final Resource _referralMethod = ResourceFactory.createResource(NS + "_referralMethod");
	public static final Resource _referralRequest = ResourceFactory.createResource(NS + "_referralRequest");
	public static final Resource _referrer = ResourceFactory.createResource(NS + "_referrer");
	public static final Resource _region = ResourceFactory.createResource(NS + "_region");
	public static final Resource _regionType = ResourceFactory.createResource(NS + "_regionType");
	public static final Resource _regionsCalled = ResourceFactory.createResource(NS + "_regionsCalled");
	public static final Resource _regionsStudied = ResourceFactory.createResource(NS + "_regionsStudied");
	public static final Resource _regulator = ResourceFactory.createResource(NS + "_regulator");
	public static final Resource _regulatory = ResourceFactory.createResource(NS + "_regulatory");
	public static final Resource _regulatoryAuthority = ResourceFactory.createResource(NS + "_regulatoryAuthority");
	public static final Resource _regulatoryBasis = ResourceFactory.createResource(NS + "_regulatoryBasis");
	public static final Resource _regulatoryIdentifier = ResourceFactory.createResource(NS + "_regulatoryIdentifier");
	public static final Resource _rejectionCriterion = ResourceFactory.createResource(NS + "_rejectionCriterion");
	public static final Resource _related = ResourceFactory.createResource(NS + "_related");
	public static final Resource _relatedAccount = ResourceFactory.createResource(NS + "_relatedAccount");
	public static final Resource _relatedAction = ResourceFactory.createResource(NS + "_relatedAction");
	public static final Resource _relatedArtifact = ResourceFactory.createResource(NS + "_relatedArtifact");
	public static final Resource _relatedClinicalInformation = ResourceFactory
			.createResource(NS + "_relatedClinicalInformation");
	public static final Resource _relatedData = ResourceFactory.createResource(NS + "_relatedData");
	public static final Resource _relatedDevice = ResourceFactory.createResource(NS + "_relatedDevice");
	public static final Resource _relatedIdentifier = ResourceFactory.createResource(NS + "_relatedIdentifier");
	public static final Resource _relatedItem = ResourceFactory.createResource(NS + "_relatedItem");
	public static final Resource _relatedMedicationKnowledge = ResourceFactory
			.createResource(NS + "_relatedMedicationKnowledge");
	public static final Resource _relatesTo = ResourceFactory.createResource(NS + "_relatesTo");
	public static final Resource _relation = ResourceFactory.createResource(NS + "_relation");
	public static final Resource _relationship = ResourceFactory.createResource(NS + "_relationship");
	public static final Resource _relationshipType = ResourceFactory.createResource(NS + "_relationshipType");
	public static final Resource _relative = ResourceFactory.createResource(NS + "_relative");
	public static final Resource _relativePath = ResourceFactory.createResource(NS + "_relativePath");
	public static final Resource _relativeRisk = ResourceFactory.createResource(NS + "_relativeRisk");
	public static final Resource _releaseDate = ResourceFactory.createResource(NS + "_releaseDate");
	public static final Resource _relevantHistory = ResourceFactory.createResource(NS + "_relevantHistory");
	public static final Resource _reliableCache = ResourceFactory.createResource(NS + "_reliableCache");
	public static final Resource _renderedDosageInstruction = ResourceFactory
			.createResource(NS + "_renderedDosageInstruction");
	public static final Resource _rendering = ResourceFactory.createResource(NS + "_rendering");
	public static final Resource _repeat = ResourceFactory.createResource(NS + "_repeat");
	public static final Resource _repeatUnit = ResourceFactory.createResource(NS + "_repeatUnit");
	public static final Resource _repeatUnitAmountType = ResourceFactory.createResource(NS + "_repeatUnitAmountType");
	public static final Resource _repeats = ResourceFactory.createResource(NS + "_repeats");
	public static final Resource _repetitions = ResourceFactory.createResource(NS + "_repetitions");
	public static final Resource _replacedSequence = ResourceFactory.createResource(NS + "_replacedSequence");
	public static final Resource _replacementSequence = ResourceFactory.createResource(NS + "_replacementSequence");
	public static final Resource _replaces = ResourceFactory.createResource(NS + "_replaces");
	public static final Resource _report = ResourceFactory.createResource(NS + "_report");
	public static final Resource _reported = ResourceFactory.createResource(NS + "_reported");
	public static final Resource _reportedDateTime = ResourceFactory.createResource(NS + "_reportedDateTime");
	public static final Resource _reporter = ResourceFactory.createResource(NS + "_reporter");
	public static final Resource _reportingPeriod = ResourceFactory.createResource(NS + "_reportingPeriod");
	public static final Resource _reportingVendor = ResourceFactory.createResource(NS + "_reportingVendor");
	public static final Resource _representation = ResourceFactory.createResource(NS + "_representation");
	public static final Resource _request = ResourceFactory.createResource(NS + "_request");
	public static final Resource _requestHeader = ResourceFactory.createResource(NS + "_requestHeader");
	public static final Resource _requestId = ResourceFactory.createResource(NS + "_requestId");
	public static final Resource _requestIdentifier = ResourceFactory.createResource(NS + "_requestIdentifier");
	public static final Resource _requestMethod = ResourceFactory.createResource(NS + "_requestMethod");
	public static final Resource _requestProvider = ResourceFactory.createResource(NS + "_requestProvider");
	public static final Resource _requestURL = ResourceFactory.createResource(NS + "_requestURL");
	public static final Resource _requestedLocation = ResourceFactory.createResource(NS + "_requestedLocation");
	public static final Resource _requestedPerformer = ResourceFactory.createResource(NS + "_requestedPerformer");
	public static final Resource _requestedPeriod = ResourceFactory.createResource(NS + "_requestedPeriod");
	public static final Resource _requester = ResourceFactory.createResource(NS + "_requester");
	public static final Resource _requesterLinkId = ResourceFactory.createResource(NS + "_requesterLinkId");
	public static final Resource _requestingOrganization = ResourceFactory
			.createResource(NS + "_requestingOrganization");
	public static final Resource _requestor = ResourceFactory.createResource(NS + "_requestor");
	public static final Resource _requireBoth = ResourceFactory.createResource(NS + "_requireBoth");
	public static final Resource _required = ResourceFactory.createResource(NS + "_required");
	public static final Resource _requiredBehavior = ResourceFactory.createResource(NS + "_requiredBehavior");
	public static final Resource _requirement = ResourceFactory.createResource(NS + "_requirement");
	public static final Resource _requirements = ResourceFactory.createResource(NS + "_requirements");
	public static final Resource _requisition = ResourceFactory.createResource(NS + "_requisition");
	public static final Resource _residueSite = ResourceFactory.createResource(NS + "_residueSite");
	public static final Resource _Resource = ResourceFactory.createResource(NS + "_resource");
	public static final Resource _resourceReference = ResourceFactory.createResource(NS + "_resourceReference");
	public static final Resource _resourceTrigger = ResourceFactory.createResource(NS + "_resourceTrigger");
	public static final Resource _resourceType = ResourceFactory.createResource(NS + "_resourceType");
	public static final Resource _response = ResourceFactory.createResource(NS + "_response");
	public static final Resource _responseCode = ResourceFactory.createResource(NS + "_responseCode");
	public static final Resource _responseId = ResourceFactory.createResource(NS + "_responseId");
	public static final Resource _responseRequired = ResourceFactory.createResource(NS + "_responseRequired");
	public static final Resource _responsibility = ResourceFactory.createResource(NS + "_responsibility");
	public static final Resource _responsible = ResourceFactory.createResource(NS + "_responsible");
	public static final Resource _responsibleOrganization = ResourceFactory
			.createResource(NS + "_responsibleOrganization");
	public static final Resource _responsibleParty = ResourceFactory.createResource(NS + "_responsibleParty");
	public static final Resource _rest = ResourceFactory.createResource(NS + "_rest");
	public static final Resource _restoreDate = ResourceFactory.createResource(NS + "_restoreDate");
	public static final Resource _restriction = ResourceFactory.createResource(NS + "_restriction");
	public static final Resource _result = ResourceFactory.createResource(NS + "_result");
	public static final Resource _resultForCreate = ResourceFactory.createResource(NS + "_resultForCreate");
	public static final Resource _resultForDelete = ResourceFactory.createResource(NS + "_resultForDelete");
	public static final Resource _resultingEffect = ResourceFactory.createResource(NS + "_resultingEffect");
	public static final Resource _resultsInterpreter = ResourceFactory.createResource(NS + "_resultsInterpreter");
	public static final Resource _retentionTime = ResourceFactory.createResource(NS + "_retentionTime");
	public static final Resource _returnedAmount = ResourceFactory.createResource(NS + "_returnedAmount");
	public static final Resource _revInclude = ResourceFactory.createResource(NS + "_revInclude");
	public static final Resource _revenue = ResourceFactory.createResource(NS + "_revenue");
	public static final Resource _reviewOutcome = ResourceFactory.createResource(NS + "_reviewOutcome");
	public static final Resource _reviewer = ResourceFactory.createResource(NS + "_reviewer");
	public static final Resource _riskAdjustment = ResourceFactory.createResource(NS + "_riskAdjustment");
	public static final Resource _role = ResourceFactory.createResource(NS + "_role");
	public static final Resource _route = ResourceFactory.createResource(NS + "_route");
	public static final Resource _routeOfAdministration = ResourceFactory.createResource(NS + "_routeOfAdministration");
	public static final Resource _rule = ResourceFactory.createResource(NS + "_rule");
	public static final Resource _rules = ResourceFactory.createResource(NS + "_rules");
	public static final Resource _safety = ResourceFactory.createResource(NS + "_safety");
	public static final Resource _sampleSize = ResourceFactory.createResource(NS + "_sampleSize");
	public static final Resource _satisfiedBy = ResourceFactory.createResource(NS + "_satisfiedBy");
	public static final Resource _saturday = ResourceFactory.createResource(NS + "_saturday");
	public static final Resource _schedule = ResourceFactory.createResource(NS + "_schedule");
	public static final Resource _scope = ResourceFactory.createResource(NS + "_scope");
	public static final Resource _score = ResourceFactory.createResource(NS + "_score");
	public static final Resource _scoring = ResourceFactory.createResource(NS + "_scoring");
	public static final Resource _scoringUnit = ResourceFactory.createResource(NS + "_scoringUnit");
	public static final Resource _script = ResourceFactory.createResource(NS + "_script");
	public static final Resource _search = ResourceFactory.createResource(NS + "_search");
	public static final Resource _searchInclude = ResourceFactory.createResource(NS + "_searchInclude");
	public static final Resource _searchParam = ResourceFactory.createResource(NS + "_searchParam");
	public static final Resource _searchRevInclude = ResourceFactory.createResource(NS + "_searchRevInclude");
	public static final Resource _searchType = ResourceFactory.createResource(NS + "_searchType");
	public static final Resource _section = ResourceFactory.createResource(NS + "_section");
	public static final Resource _security = ResourceFactory.createResource(NS + "_security");
	public static final Resource _securityContext = ResourceFactory.createResource(NS + "_securityContext");
	public static final Resource _securityLabel = ResourceFactory.createResource(NS + "_securityLabel");
	public static final Resource _securityLabelNumber = ResourceFactory.createResource(NS + "_securityLabelNumber");
	public static final Resource _selectionBehavior = ResourceFactory.createResource(NS + "_selectionBehavior");
	public static final Resource _sender = ResourceFactory.createResource(NS + "_sender");
	public static final Resource _sent = ResourceFactory.createResource(NS + "_sent");
	public static final Resource _sequence = ResourceFactory.createResource(NS + "_sequence");
	public static final Resource _sequenceAttachment = ResourceFactory.createResource(NS + "_sequenceAttachment");
	public static final Resource _sequenceRange = ResourceFactory.createResource(NS + "_sequenceRange");
	public static final Resource _sequenceType = ResourceFactory.createResource(NS + "_sequenceType");
	public static final Resource _serialNumber = ResourceFactory.createResource(NS + "_serialNumber");
	public static final Resource _series = ResourceFactory.createResource(NS + "_series");
	public static final Resource _seriesDoses = ResourceFactory.createResource(NS + "_seriesDoses");
	public static final Resource _seriesNumber = ResourceFactory.createResource(NS + "_seriesNumber");
	public static final Resource _seriesUid = ResourceFactory.createResource(NS + "_seriesUid");
	public static final Resource _seriousness = ResourceFactory.createResource(NS + "_seriousness");
	public static final Resource _service = ResourceFactory.createResource(NS + "_service");
	public static final Resource _serviceCategory = ResourceFactory.createResource(NS + "_serviceCategory");
	public static final Resource _servicePeriod = ResourceFactory.createResource(NS + "_servicePeriod");
	public static final Resource _serviceProvider = ResourceFactory.createResource(NS + "_serviceProvider");
	public static final Resource _serviceProvisionCode = ResourceFactory.createResource(NS + "_serviceProvisionCode");
	public static final Resource _serviceType = ResourceFactory.createResource(NS + "_serviceType");
	public static final Resource _serviced = ResourceFactory.createResource(NS + "_serviced");
	public static final Resource _sessionKey = ResourceFactory.createResource(NS + "_sessionKey");
	public static final Resource _setup = ResourceFactory.createResource(NS + "_setup");
	public static final Resource _severity = ResourceFactory.createResource(NS + "_severity");
	public static final Resource _sex = ResourceFactory.createResource(NS + "_sex");
	public static final Resource _shelfLifeStorage = ResourceFactory.createResource(NS + "_shelfLifeStorage");
	public static final Resource _short = ResourceFactory.createResource(NS + "_short");
	public static final Resource _shortDoco = ResourceFactory.createResource(NS + "_shortDoco");
	public static final Resource _shortTitle = ResourceFactory.createResource(NS + "_shortTitle");
	public static final Resource _sigFormat = ResourceFactory.createResource(NS + "_sigFormat");
	public static final Resource _signature = ResourceFactory.createResource(NS + "_signature");
	public static final Resource _signer = ResourceFactory.createResource(NS + "_signer");
	public static final Resource _singleUse = ResourceFactory.createResource(NS + "_singleUse");
	public static final Resource _site = ResourceFactory.createResource(NS + "_site");
	public static final Resource _situation = ResourceFactory.createResource(NS + "_situation");
	public static final Resource _size = ResourceFactory.createResource(NS + "_size");
	public static final Resource _sliceIsConstraining = ResourceFactory.createResource(NS + "_sliceIsConstraining");
	public static final Resource _sliceName = ResourceFactory.createResource(NS + "_sliceName");
	public static final Resource _slicing = ResourceFactory.createResource(NS + "_slicing");
	public static final Resource _slot = ResourceFactory.createResource(NS + "_slot");
	public static final Resource _snapshot = ResourceFactory.createResource(NS + "_snapshot");
	public static final Resource _software = ResourceFactory.createResource(NS + "_software");
	public static final Resource _sopClass = ResourceFactory.createResource(NS + "_sopClass");
	public static final Resource _sort = ResourceFactory.createResource(NS + "_sort");
	public static final Resource _source = ResourceFactory.createResource(NS + "_source");
	public static final Resource _sourceAttachment = ResourceFactory.createResource(NS + "_sourceAttachment");
	public static final Resource _sourceDocument = ResourceFactory.createResource(NS + "_sourceDocument");
	public static final Resource _sourceId = ResourceFactory.createResource(NS + "_sourceId");
	public static final Resource _sourceIdentityCertificate = ResourceFactory
			.createResource(NS + "_sourceIdentityCertificate");
	public static final Resource _sourceMaterial = ResourceFactory.createResource(NS + "_sourceMaterial");
	public static final Resource _sourceMaterialClass = ResourceFactory.createResource(NS + "_sourceMaterialClass");
	public static final Resource _sourceMaterialState = ResourceFactory.createResource(NS + "_sourceMaterialState");
	public static final Resource _sourceMaterialType = ResourceFactory.createResource(NS + "_sourceMaterialType");
	public static final Resource _sourceReference = ResourceFactory.createResource(NS + "_sourceReference");
	public static final Resource _sourceScope = ResourceFactory.createResource(NS + "_sourceScope");
	public static final Resource _sourceSignature = ResourceFactory.createResource(NS + "_sourceSignature");
	public static final Resource _spatialReference = ResourceFactory.createResource(NS + "_spatialReference");
	public static final Resource _specialArrangement = ResourceFactory.createResource(NS + "_specialArrangement");
	public static final Resource _specialCourtesy = ResourceFactory.createResource(NS + "_specialCourtesy");
	public static final Resource _specialMeasures = ResourceFactory.createResource(NS + "_specialMeasures");
	public static final Resource _specialPrecautionsForStorage = ResourceFactory
			.createResource(NS + "_specialPrecautionsForStorage");
	public static final Resource _specialty = ResourceFactory.createResource(NS + "_specialty");
	public static final Resource _species = ResourceFactory.createResource(NS + "_species");
	public static final Resource _specificCost = ResourceFactory.createResource(NS + "_specificCost");
	public static final Resource _specification = ResourceFactory.createResource(NS + "_specification");
	public static final Resource _specimen = ResourceFactory.createResource(NS + "_specimen");
	public static final Resource _specimenQuantity = ResourceFactory.createResource(NS + "_specimenQuantity");
	public static final Resource _specimenRequirement = ResourceFactory.createResource(NS + "_specimenRequirement");
	public static final Resource _sphere = ResourceFactory.createResource(NS + "_sphere");
	public static final Resource _stabilityDuration = ResourceFactory.createResource(NS + "_stabilityDuration");
	public static final Resource _stage = ResourceFactory.createResource(NS + "_stage");
	public static final Resource _start = ResourceFactory.createResource(NS + "_start");
	public static final Resource _startDate = ResourceFactory.createResource(NS + "_startDate");
	public static final Resource _startParam = ResourceFactory.createResource(NS + "_startParam");
	public static final Resource _started = ResourceFactory.createResource(NS + "_started");
	public static final Resource _startingMaterial = ResourceFactory.createResource(NS + "_startingMaterial");
	public static final Resource _startingSequence = ResourceFactory.createResource(NS + "_startingSequence");
	public static final Resource _state = ResourceFactory.createResource(NS + "_state");
	public static final Resource _statement = ResourceFactory.createResource(NS + "_statement");
	public static final Resource _statistic = ResourceFactory.createResource(NS + "_statistic");
	public static final Resource _statisticType = ResourceFactory.createResource(NS + "_statisticType");
	public static final Resource _status = ResourceFactory.createResource(NS + "_status");
	public static final Resource _statusChanged = ResourceFactory.createResource(NS + "_statusChanged");
	public static final Resource _statusDate = ResourceFactory.createResource(NS + "_statusDate");
	public static final Resource _statusHistory = ResourceFactory.createResource(NS + "_statusHistory");
	public static final Resource _statusReason = ResourceFactory.createResource(NS + "_statusReason");
	public static final Resource _step = ResourceFactory.createResource(NS + "_step");
	public static final Resource _stereochemistry = ResourceFactory.createResource(NS + "_stereochemistry");
	public static final Resource _stopTestOnFail = ResourceFactory.createResource(NS + "_stopTestOnFail");
	public static final Resource _storageGuideline = ResourceFactory.createResource(NS + "_storageGuideline");
	public static final Resource _storageTempRequirements = ResourceFactory
			.createResource(NS + "_storageTempRequirements");
	public static final Resource _strand = ResourceFactory.createResource(NS + "_strand");
	public static final Resource _stratifier = ResourceFactory.createResource(NS + "_stratifier");
	public static final Resource _stratum = ResourceFactory.createResource(NS + "_stratum");
	public static final Resource _strength = ResourceFactory.createResource(NS + "_strength");
	public static final Resource _structuralRepresentation = ResourceFactory
			.createResource(NS + "_structuralRepresentation");
	public static final Resource _structure = ResourceFactory.createResource(NS + "_structure");
	public static final Resource _structureProfile = ResourceFactory.createResource(NS + "_structureProfile");
	public static final Resource _structureType = ResourceFactory.createResource(NS + "_structureType");
	public static final Resource _structureVersion = ResourceFactory.createResource(NS + "_structureVersion");
	public static final Resource _study = ResourceFactory.createResource(NS + "_study");
	public static final Resource _studyDesign = ResourceFactory.createResource(NS + "_studyDesign");
	public static final Resource _studyUid = ResourceFactory.createResource(NS + "_studyUid");
	public static final Resource _style = ResourceFactory.createResource(NS + "_style");
	public static final Resource _subDetail = ResourceFactory.createResource(NS + "_subDetail");
	public static final Resource _subDetailSequence = ResourceFactory.createResource(NS + "_subDetailSequence");
	public static final Resource _subJurisdiction = ResourceFactory.createResource(NS + "_subJurisdiction");
	public static final Resource _subPotentReason = ResourceFactory.createResource(NS + "_subPotentReason");
	public static final Resource _subProperty = ResourceFactory.createResource(NS + "_subProperty");
	public static final Resource _subSite = ResourceFactory.createResource(NS + "_subSite");
	public static final Resource _subType = ResourceFactory.createResource(NS + "_subType");
	public static final Resource _subcomponent = ResourceFactory.createResource(NS + "_subcomponent");
	public static final Resource _subdetailSequence = ResourceFactory.createResource(NS + "_subdetailSequence");
	public static final Resource _subject = ResourceFactory.createResource(NS + "_subject");
	public static final Resource _subjectReport = ResourceFactory.createResource(NS + "_subjectReport");
	public static final Resource _subjectResults = ResourceFactory.createResource(NS + "_subjectResults");
	public static final Resource _subjectState = ResourceFactory.createResource(NS + "_subjectState");
	public static final Resource _subjectStatus = ResourceFactory.createResource(NS + "_subjectStatus");
	public static final Resource _subjectType = ResourceFactory.createResource(NS + "_subjectType");
	public static final Resource _subjects = ResourceFactory.createResource(NS + "_subjects");
	public static final Resource _submitter = ResourceFactory.createResource(NS + "_submitter");
	public static final Resource _subpotentReason = ResourceFactory.createResource(NS + "_subpotentReason");
	public static final Resource _subrogation = ResourceFactory.createResource(NS + "_subrogation");
	public static final Resource _subscriber = ResourceFactory.createResource(NS + "_subscriber");
	public static final Resource _subscriberId = ResourceFactory.createResource(NS + "_subscriberId");
	public static final Resource _subscription = ResourceFactory.createResource(NS + "_subscription");
	public static final Resource _subscriptionTopic = ResourceFactory.createResource(NS + "_subscriptionTopic");
	public static final Resource _subset = ResourceFactory.createResource(NS + "_subset");
	public static final Resource _substance = ResourceFactory.createResource(NS + "_substance");
	public static final Resource _substanceDefinition = ResourceFactory.createResource(NS + "_substanceDefinition");
	public static final Resource _substitution = ResourceFactory.createResource(NS + "_substitution");
	public static final Resource _subsumption = ResourceFactory.createResource(NS + "_subsumption");
	public static final Resource _subtitle = ResourceFactory.createResource(NS + "_subtitle");
	public static final Resource _subtype = ResourceFactory.createResource(NS + "_subtype");
	public static final Resource _subunit = ResourceFactory.createResource(NS + "_subunit");
	public static final Resource _suffix = ResourceFactory.createResource(NS + "_suffix");
	public static final Resource _sugar = ResourceFactory.createResource(NS + "_sugar");
	public static final Resource _summary = ResourceFactory.createResource(NS + "_summary");
	public static final Resource _sunday = ResourceFactory.createResource(NS + "_sunday");
	public static final Resource _supplement = ResourceFactory.createResource(NS + "_supplement");
	public static final Resource _supplementalData = ResourceFactory.createResource(NS + "_supplementalData");
	public static final Resource _supplements = ResourceFactory.createResource(NS + "_supplements");
	public static final Resource _suppliedItem = ResourceFactory.createResource(NS + "_suppliedItem");
	public static final Resource _supplier = ResourceFactory.createResource(NS + "_supplier");
	public static final Resource _supportedInteraction = ResourceFactory.createResource(NS + "_supportedInteraction");
	public static final Resource _supportedMessage = ResourceFactory.createResource(NS + "_supportedMessage");
	public static final Resource _supportedProfile = ResourceFactory.createResource(NS + "_supportedProfile");
	public static final Resource _supportingImmunization = ResourceFactory
			.createResource(NS + "_supportingImmunization");
	public static final Resource _supportingInfo = ResourceFactory.createResource(NS + "_supportingInfo");
	public static final Resource _supportingInfoSequence = ResourceFactory
			.createResource(NS + "_supportingInfoSequence");
	public static final Resource _supportingInformation = ResourceFactory.createResource(NS + "_supportingInformation");
	public static final Resource _supportingPatientInformation = ResourceFactory
			.createResource(NS + "_supportingPatientInformation");
	public static final Resource _suppress = ResourceFactory.createResource(NS + "_suppress");
	public static final Resource _surfaceOrientation = ResourceFactory.createResource(NS + "_surfaceOrientation");
	public static final Resource _suspectEntity = ResourceFactory.createResource(NS + "_suspectEntity");
	public static final Resource _symptomConditionEffect = ResourceFactory
			.createResource(NS + "_symptomConditionEffect");
	public static final Resource _synonym = ResourceFactory.createResource(NS + "_synonym");
	public static final Resource _synthesisType = ResourceFactory.createResource(NS + "_synthesisType");
	public static final Resource _system = ResourceFactory.createResource(NS + "_system");
	public static final Resource _tag = ResourceFactory.createResource(NS + "_tag");
	public static final Resource _target = ResourceFactory.createResource(NS + "_target");
	public static final Resource _targetDisease = ResourceFactory.createResource(NS + "_targetDisease");
	public static final Resource _targetFormat = ResourceFactory.createResource(NS + "_targetFormat");
	public static final Resource _targetId = ResourceFactory.createResource(NS + "_targetId");
	public static final Resource _targetItem = ResourceFactory.createResource(NS + "_targetItem");
	public static final Resource _targetLocation = ResourceFactory.createResource(NS + "_targetLocation");
	public static final Resource _targetNumber = ResourceFactory.createResource(NS + "_targetNumber");
	public static final Resource _targetProfile = ResourceFactory.createResource(NS + "_targetProfile");
	public static final Resource _targetScope = ResourceFactory.createResource(NS + "_targetScope");
	public static final Resource _targetSpecies = ResourceFactory.createResource(NS + "_targetSpecies");
	public static final Resource _tax = ResourceFactory.createResource(NS + "_tax");
	public static final Resource _team = ResourceFactory.createResource(NS + "_team");
	public static final Resource _teardown = ResourceFactory.createResource(NS + "_teardown");
	public static final Resource _technique = ResourceFactory.createResource(NS + "_technique");
	public static final Resource _telecom = ResourceFactory.createResource(NS + "_telecom");
	public static final Resource _temperatureQualifier = ResourceFactory.createResource(NS + "_temperatureQualifier");
	public static final Resource _temperatureRange = ResourceFactory.createResource(NS + "_temperatureRange");
	public static final Resource _template = ResourceFactory.createResource(NS + "_template");
	public static final Resource _tenderedAmount = ResourceFactory.createResource(NS + "_tenderedAmount");
	public static final Resource _term = ResourceFactory.createResource(NS + "_term");
	public static final Resource _test = ResourceFactory.createResource(NS + "_test");
	public static final Resource _testCase = ResourceFactory.createResource(NS + "_testCase");
	public static final Resource _testData = ResourceFactory.createResource(NS + "_testData");
	public static final Resource _testRun = ResourceFactory.createResource(NS + "_testRun");
	public static final Resource _testScript = ResourceFactory.createResource(NS + "_testScript");
	public static final Resource _testTools = ResourceFactory.createResource(NS + "_testTools");
	public static final Resource _tester = ResourceFactory.createResource(NS + "_tester");
	public static final Resource _testingDestination = ResourceFactory.createResource(NS + "_testingDestination");
	public static final Resource _text = ResourceFactory.createResource(NS + "_text");
	public static final Resource _textConcentration = ResourceFactory.createResource(NS + "_textConcentration");
	public static final Resource _textEquivalent = ResourceFactory.createResource(NS + "_textEquivalent");
	public static final Resource _textFilter = ResourceFactory.createResource(NS + "_textFilter");
	public static final Resource _textPresentation = ResourceFactory.createResource(NS + "_textPresentation");
	public static final Resource _texture = ResourceFactory.createResource(NS + "_texture");
	public static final Resource _threePrime = ResourceFactory.createResource(NS + "_threePrime");
	public static final Resource _threshold = ResourceFactory.createResource(NS + "_threshold");
	public static final Resource _thursday = ResourceFactory.createResource(NS + "_thursday");
	public static final Resource _time = ResourceFactory.createResource(NS + "_time");
	public static final Resource _timeAspect = ResourceFactory.createResource(NS + "_timeAspect");
	public static final Resource _timeFromEvent = ResourceFactory.createResource(NS + "_timeFromEvent");
	public static final Resource _timeOfDay = ResourceFactory.createResource(NS + "_timeOfDay");
	public static final Resource _timeout = ResourceFactory.createResource(NS + "_timeout");
	public static final Resource _timestamp = ResourceFactory.createResource(NS + "_timestamp");
	public static final Resource _timezone = ResourceFactory.createResource(NS + "_timezone");
	public static final Resource _timing = ResourceFactory.createResource(NS + "_timing");
	public static final Resource _tissue = ResourceFactory.createResource(NS + "_tissue");
	public static final Resource _title = ResourceFactory.createResource(NS + "_title");
	public static final Resource _topic = ResourceFactory.createResource(NS + "_topic");
	public static final Resource _total = ResourceFactory.createResource(NS + "_total");
	public static final Resource _totalGross = ResourceFactory.createResource(NS + "_totalGross");
	public static final Resource _totalNet = ResourceFactory.createResource(NS + "_totalNet");
	public static final Resource _totalPriceComponent = ResourceFactory.createResource(NS + "_totalPriceComponent");
	public static final Resource _totalVolume = ResourceFactory.createResource(NS + "_totalVolume");
	public static final Resource _traceNumber = ResourceFactory.createResource(NS + "_traceNumber");
	public static final Resource _transform = ResourceFactory.createResource(NS + "_transform");
	public static final Resource _translation = ResourceFactory.createResource(NS + "_translation");
	public static final Resource _translations = ResourceFactory.createResource(NS + "_translations");
	public static final Resource _treatment = ResourceFactory.createResource(NS + "_treatment");
	public static final Resource _treatmentIntent = ResourceFactory.createResource(NS + "_treatmentIntent");
	public static final Resource _trigger = ResourceFactory.createResource(NS + "_trigger");
	public static final Resource _triggeredBy = ResourceFactory.createResource(NS + "_triggeredBy");
	public static final Resource _tuesday = ResourceFactory.createResource(NS + "_tuesday");
	public static final Resource _type = ResourceFactory.createResource(NS + "_type");
	public static final Resource _typeCanonical = ResourceFactory.createResource(NS + "_typeCanonical");
	public static final Resource _typeCollected = ResourceFactory.createResource(NS + "_typeCollected");
	public static final Resource _typeMode = ResourceFactory.createResource(NS + "_typeMode");
	public static final Resource _typeReference = ResourceFactory.createResource(NS + "_typeReference");
	public static final Resource _typeTested = ResourceFactory.createResource(NS + "_typeTested");
	public static final Resource _udi = ResourceFactory.createResource(NS + "_udi");
	public static final Resource _udiCarrier = ResourceFactory.createResource(NS + "_udiCarrier");
	public static final Resource _udiDeviceIdentifier = ResourceFactory.createResource(NS + "_udiDeviceIdentifier");
	public static final Resource _uid = ResourceFactory.createResource(NS + "_uid");
	public static final Resource _undesirableEffect = ResourceFactory.createResource(NS + "_undesirableEffect");
	public static final Resource _uniqueId = ResourceFactory.createResource(NS + "_uniqueId");
	public static final Resource _unit = ResourceFactory.createResource(NS + "_unit");
	public static final Resource _unitOfPresentation = ResourceFactory.createResource(NS + "_unitOfPresentation");
	public static final Resource _unitPrice = ResourceFactory.createResource(NS + "_unitPrice");
	public static final Resource _unitPriceComponent = ResourceFactory.createResource(NS + "_unitPriceComponent");
	public static final Resource _unmapped = ResourceFactory.createResource(NS + "_unmapped");
	public static final Resource _updateCreate = ResourceFactory.createResource(NS + "_updateCreate");
	public static final Resource _upperLimit = ResourceFactory.createResource(NS + "_upperLimit");
	public static final Resource _uri = ResourceFactory.createResource(NS + "_uri");
	public static final Resource _url = ResourceFactory.createResource(NS + "_url");
	public static final Resource _usage = ResourceFactory.createResource(NS + "_usage");
	public static final Resource _usageInstruction = ResourceFactory.createResource(NS + "_usageInstruction");
	public static final Resource _usageReason = ResourceFactory.createResource(NS + "_usageReason");
	public static final Resource _usageStatus = ResourceFactory.createResource(NS + "_usageStatus");
	public static final Resource _use = ResourceFactory.createResource(NS + "_use");
	public static final Resource _useBy = ResourceFactory.createResource(NS + "_useBy");
	public static final Resource _useContext = ResourceFactory.createResource(NS + "_useContext");
	public static final Resource _usePeriod = ResourceFactory.createResource(NS + "_usePeriod");
	public static final Resource _used = ResourceFactory.createResource(NS + "_used");
	public static final Resource _userSelected = ResourceFactory.createResource(NS + "_userSelected");
	public static final Resource _vaccineCode = ResourceFactory.createResource(NS + "_vaccineCode");
	public static final Resource _validCodedValueSet = ResourceFactory.createResource(NS + "_validCodedValueSet");
	public static final Resource _validateCode = ResourceFactory.createResource(NS + "_validateCode");
	public static final Resource _validateProfileId = ResourceFactory.createResource(NS + "_validateProfileId");
	public static final Resource _validated = ResourceFactory.createResource(NS + "_validated");
	public static final Resource _validationDate = ResourceFactory.createResource(NS + "_validationDate");
	public static final Resource _validationProcess = ResourceFactory.createResource(NS + "_validationProcess");
	public static final Resource _validationStatus = ResourceFactory.createResource(NS + "_validationStatus");
	public static final Resource _validationType = ResourceFactory.createResource(NS + "_validationType");
	public static final Resource _validator = ResourceFactory.createResource(NS + "_validator");
	public static final Resource _validity = ResourceFactory.createResource(NS + "_validity");
	public static final Resource _validityPeriod = ResourceFactory.createResource(NS + "_validityPeriod");
	public static final Resource _value = ResourceFactory.createResource(NS + "_value");
	public static final Resource _valueAlternatives = ResourceFactory.createResource(NS + "_valueAlternatives");
	public static final Resource _valueCategory = ResourceFactory.createResource(NS + "_valueCategory");
	public static final Resource _valueFilter = ResourceFactory.createResource(NS + "_valueFilter");
	public static final Resource _valueQuantity = ResourceFactory.createResource(NS + "_valueQuantity");
	public static final Resource _valueRange = ResourceFactory.createResource(NS + "_valueRange");
	public static final Resource _valueSet = ResourceFactory.createResource(NS + "_valueSet");
	public static final Resource _valuedItem = ResourceFactory.createResource(NS + "_valuedItem");
	public static final Resource _variable = ResourceFactory.createResource(NS + "_variable");
	public static final Resource _variableDefinition = ResourceFactory.createResource(NS + "_variableDefinition");
	public static final Resource _variableRole = ResourceFactory.createResource(NS + "_variableRole");
	public static final Resource _verification = ResourceFactory.createResource(NS + "_verification");
	public static final Resource _verificationDate = ResourceFactory.createResource(NS + "_verificationDate");
	public static final Resource _verificationStatus = ResourceFactory.createResource(NS + "_verificationStatus");
	public static final Resource _verificationType = ResourceFactory.createResource(NS + "_verificationType");
	public static final Resource _verified = ResourceFactory.createResource(NS + "_verified");
	public static final Resource _verifiedBy = ResourceFactory.createResource(NS + "_verifiedBy");
	public static final Resource _verifiedWith = ResourceFactory.createResource(NS + "_verifiedWith");
	public static final Resource _version = ResourceFactory.createResource(NS + "_version");
	public static final Resource _versionAlgorithm = ResourceFactory.createResource(NS + "_versionAlgorithm");
	public static final Resource _versionId = ResourceFactory.createResource(NS + "_versionId");
	public static final Resource _versionNeeded = ResourceFactory.createResource(NS + "_versionNeeded");
	public static final Resource _versionReference = ResourceFactory.createResource(NS + "_versionReference");
	public static final Resource _versioning = ResourceFactory.createResource(NS + "_versioning");
	public static final Resource _virtualService = ResourceFactory.createResource(NS + "_virtualService");
	public static final Resource _volume = ResourceFactory.createResource(NS + "_volume");
	public static final Resource _warning = ResourceFactory.createResource(NS + "_warning");
	public static final Resource _warningOnly = ResourceFactory.createResource(NS + "_warningOnly");
	public static final Resource _wasSubstituted = ResourceFactory.createResource(NS + "_wasSubstituted");
	public static final Resource _webLocation = ResourceFactory.createResource(NS + "_webLocation");
	public static final Resource _wednesday = ResourceFactory.createResource(NS + "_wednesday");
	public static final Resource _weekInterval = ResourceFactory.createResource(NS + "_weekInterval");
	public static final Resource _weeklyTemplate = ResourceFactory.createResource(NS + "_weeklyTemplate");
	public static final Resource _what = ResourceFactory.createResource(NS + "_what");
	public static final Resource _when = ResourceFactory.createResource(NS + "_when");
	public static final Resource _whenHandedOver = ResourceFactory.createResource(NS + "_whenHandedOver");
	public static final Resource _whenPrepared = ResourceFactory.createResource(NS + "_whenPrepared");
	public static final Resource _who = ResourceFactory.createResource(NS + "_who");
	public static final Resource _whyStopped = ResourceFactory.createResource(NS + "_whyStopped");
	public static final Resource _width = ResourceFactory.createResource(NS + "_width");
	public static final Resource _windowEnd = ResourceFactory.createResource(NS + "_windowEnd");
	public static final Resource _windowStart = ResourceFactory.createResource(NS + "_windowStart");
	public static final Resource _withdrawalPeriod = ResourceFactory.createResource(NS + "_withdrawalPeriod");
	public static final Resource _workflow = ResourceFactory.createResource(NS + "_workflow");
	public static final Resource _workflowStatus = ResourceFactory.createResource(NS + "_workflowStatus");
	public static final Resource _yearInterval = ResourceFactory.createResource(NS + "_yearInterval");
	public static final Resource _yearlyTemplate = ResourceFactory.createResource(NS + "_yearlyTemplate");
	public static final Resource abatement = ResourceFactory.createResource(NS + "abatement");
	public static final Resource abnormalCodedValueSet = ResourceFactory.createResource(NS + "abnormalCodedValueSet");
	public static final Resource about = ResourceFactory.createResource(NS + "about");
	public static final Resource fhir_abstract = ResourceFactory.createResource(NS + "abstract");
	public static final Resource accept = ResourceFactory.createResource(NS + "accept");
	public static final Resource acceptLanguage = ResourceFactory.createResource(NS + "acceptLanguage");
	public static final Resource accessionIdentifier = ResourceFactory.createResource(NS + "accessionIdentifier");
	public static final Resource accessionNumber = ResourceFactory.createResource(NS + "accessionNumber");
	public static final Resource accident = ResourceFactory.createResource(NS + "accident");
	public static final Resource account = ResourceFactory.createResource(NS + "account");
	public static final Resource accountNumber = ResourceFactory.createResource(NS + "accountNumber");
	public static final Resource achievementStatus = ResourceFactory.createResource(NS + "achievementStatus");
	public static final Resource action = ResourceFactory.createResource(NS + "action");
	public static final Resource active = ResourceFactory.createResource(NS + "active");
	public static final Resource activity = ResourceFactory.createResource(NS + "activity");
	public static final Resource actor = ResourceFactory.createResource(NS + "actor");
	public static final Resource actorId = ResourceFactory.createResource(NS + "actorId");
	public static final Resource actual = ResourceFactory.createResource(NS + "actual");
	public static final Resource actualComparisonGroup = ResourceFactory.createResource(NS + "actualComparisonGroup");
	public static final Resource actualGroup = ResourceFactory.createResource(NS + "actualGroup");
	public static final Resource actualNumber = ResourceFactory.createResource(NS + "actualNumber");
	public static final Resource actualPeriod = ResourceFactory.createResource(NS + "actualPeriod");
	public static final Resource actuality = ResourceFactory.createResource(NS + "actuality");
	public static final Resource add = ResourceFactory.createResource(NS + "add");
	public static final Resource addItem = ResourceFactory.createResource(NS + "addItem");
	public static final Resource additional = ResourceFactory.createResource(NS + "additional");
	public static final Resource additionalAttribute = ResourceFactory.createResource(NS + "additionalAttribute");
	public static final Resource additionalContext = ResourceFactory.createResource(NS + "additionalContext");
	public static final Resource additionalInfo = ResourceFactory.createResource(NS + "additionalInfo");
	public static final Resource additionalInstruction = ResourceFactory.createResource(NS + "additionalInstruction");
	public static final Resource additionalMonitoringIndicator = ResourceFactory
			.createResource(NS + "additionalMonitoringIndicator");
	public static final Resource additionalUse = ResourceFactory.createResource(NS + "additionalUse");
	public static final Resource additive = ResourceFactory.createResource(NS + "additive");
	public static final Resource address = ResourceFactory.createResource(NS + "address");
	public static final Resource addresses = ResourceFactory.createResource(NS + "addresses");
	public static final Resource adherence = ResourceFactory.createResource(NS + "adherence");
	public static final Resource adjudication = ResourceFactory.createResource(NS + "adjudication");
	public static final Resource adjustment = ResourceFactory.createResource(NS + "adjustment");
	public static final Resource adjustmentReason = ResourceFactory.createResource(NS + "adjustmentReason");
	public static final Resource administeredBy = ResourceFactory.createResource(NS + "administeredBy");
	public static final Resource administeredProduct = ResourceFactory.createResource(NS + "administeredProduct");
	public static final Resource administrableDoseForm = ResourceFactory.createResource(NS + "administrableDoseForm");
	public static final Resource administration = ResourceFactory.createResource(NS + "administration");
	public static final Resource administrationInstruction = ResourceFactory
			.createResource(NS + "administrationInstruction");
	public static final Resource administrationTreatment = ResourceFactory
			.createResource(NS + "administrationTreatment");
	public static final Resource admission = ResourceFactory.createResource(NS + "admission");
	public static final Resource admitSource = ResourceFactory.createResource(NS + "admitSource");
	public static final Resource affectsState = ResourceFactory.createResource(NS + "affectsState");
	public static final Resource affiliation = ResourceFactory.createResource(NS + "affiliation");
	public static final Resource age = ResourceFactory.createResource(NS + "age");
	public static final Resource agent = ResourceFactory.createResource(NS + "agent");
	public static final Resource aggregate = ResourceFactory.createResource(NS + "aggregate");
	public static final Resource aggregateMethod = ResourceFactory.createResource(NS + "aggregateMethod");
	public static final Resource aggregation = ResourceFactory.createResource(NS + "aggregation");
	public static final Resource alias = ResourceFactory.createResource(NS + "alias");
	public static final Resource allDay = ResourceFactory.createResource(NS + "allDay");
	public static final Resource allergenicIndicator = ResourceFactory.createResource(NS + "allergenicIndicator");
	public static final Resource allergyIntolerance = ResourceFactory.createResource(NS + "allergyIntolerance");
	public static final Resource allocation = ResourceFactory.createResource(NS + "allocation");
	public static final Resource allowed = ResourceFactory.createResource(NS + "allowed");
	public static final Resource allowedResponse = ResourceFactory.createResource(NS + "allowedResponse");
	public static final Resource allowedType = ResourceFactory.createResource(NS + "allowedType");
	public static final Resource alternate = ResourceFactory.createResource(NS + "alternate");
	public static final Resource alternateMaterial = ResourceFactory.createResource(NS + "alternateMaterial");
	public static final Resource alternative = ResourceFactory.createResource(NS + "alternative");
	public static final Resource altitude = ResourceFactory.createResource(NS + "altitude");
	public static final Resource amount = ResourceFactory.createResource(NS + "amount");
	public static final Resource amountType = ResourceFactory.createResource(NS + "amountType");
	public static final Resource analysis = ResourceFactory.createResource(NS + "analysis");
	public static final Resource anchor = ResourceFactory.createResource(NS + "anchor");
	public static final Resource answer = ResourceFactory.createResource(NS + "answer");
	public static final Resource answerConstraint = ResourceFactory.createResource(NS + "answerConstraint");
	public static final Resource answerOption = ResourceFactory.createResource(NS + "answerOption");
	public static final Resource answerValueSet = ResourceFactory.createResource(NS + "answerValueSet");
	public static final Resource any = ResourceFactory.createResource(NS + "any");
	public static final Resource applicability = ResourceFactory.createResource(NS + "applicability");
	public static final Resource application = ResourceFactory.createResource(NS + "application");
	public static final Resource applied = ResourceFactory.createResource(NS + "applied");
	public static final Resource applies = ResourceFactory.createResource(NS + "applies");
	public static final Resource appliesTo = ResourceFactory.createResource(NS + "appliesTo");
	public static final Resource appliesToAll = ResourceFactory.createResource(NS + "appliesToAll");
	public static final Resource appointment = ResourceFactory.createResource(NS + "appointment");
	public static final Resource appointmentRequired = ResourceFactory.createResource(NS + "appointmentRequired");
	public static final Resource appointmentType = ResourceFactory.createResource(NS + "appointmentType");
	public static final Resource approvalDate = ResourceFactory.createResource(NS + "approvalDate");
	public static final Resource areaOfHybridisation = ResourceFactory.createResource(NS + "areaOfHybridisation");
	public static final Resource articleDate = ResourceFactory.createResource(NS + "articleDate");
	public static final Resource artifact = ResourceFactory.createResource(NS + "artifact");
	public static final Resource artifactAssessment = ResourceFactory.createResource(NS + "artifactAssessment");
	public static final Resource asNeeded = ResourceFactory.createResource(NS + "asNeeded");
	public static final Resource asNeededFor = ResourceFactory.createResource(NS + "asNeededFor");
	public static final Resource fhir_assert = ResourceFactory.createResource(NS + "assert");
	public static final Resource asserter = ResourceFactory.createResource(NS + "asserter");
	public static final Resource assertion = ResourceFactory.createResource(NS + "assertion");
	public static final Resource assessment = ResourceFactory.createResource(NS + "assessment");
	public static final Resource assessmentMethod = ResourceFactory.createResource(NS + "assessmentMethod");
	public static final Resource asset = ResourceFactory.createResource(NS + "asset");
	public static final Resource assignedComparisonGroup = ResourceFactory
			.createResource(NS + "assignedComparisonGroup");
	public static final Resource assigner = ResourceFactory.createResource(NS + "assigner");
	public static final Resource associatedMedication = ResourceFactory.createResource(NS + "associatedMedication");
	public static final Resource associatedParty = ResourceFactory.createResource(NS + "associatedParty");
	public static final Resource association = ResourceFactory.createResource(NS + "association");
	public static final Resource associationType = ResourceFactory.createResource(NS + "associationType");
	public static final Resource assurance = ResourceFactory.createResource(NS + "assurance");
	public static final Resource attachedDocument = ResourceFactory.createResource(NS + "attachedDocument");
	public static final Resource attachment = ResourceFactory.createResource(NS + "attachment");
	public static final Resource attestation = ResourceFactory.createResource(NS + "attestation");
	public static final Resource attestationSignature = ResourceFactory.createResource(NS + "attestationSignature");
	public static final Resource attester = ResourceFactory.createResource(NS + "attester");
	public static final Resource attribute = ResourceFactory.createResource(NS + "attribute");
	public static final Resource attributeEstimate = ResourceFactory.createResource(NS + "attributeEstimate");
	public static final Resource author = ResourceFactory.createResource(NS + "author");
	public static final Resource authorDescription = ResourceFactory.createResource(NS + "authorDescription");
	public static final Resource authorType = ResourceFactory.createResource(NS + "authorType");
	public static final Resource authored = ResourceFactory.createResource(NS + "authored");
	public static final Resource authoredOn = ResourceFactory.createResource(NS + "authoredOn");
	public static final Resource authoritative = ResourceFactory.createResource(NS + "authoritative");
	public static final Resource authority = ResourceFactory.createResource(NS + "authority");
	public static final Resource authorization = ResourceFactory.createResource(NS + "authorization");
	public static final Resource authorizationRequired = ResourceFactory.createResource(NS + "authorizationRequired");
	public static final Resource authorizationSupporting = ResourceFactory
			.createResource(NS + "authorizationSupporting");
	public static final Resource authorizationUrl = ResourceFactory.createResource(NS + "authorizationUrl");
	public static final Resource authorizingPrescription = ResourceFactory
			.createResource(NS + "authorizingPrescription");
	public static final Resource autocreate = ResourceFactory.createResource(NS + "autocreate");
	public static final Resource autodelete = ResourceFactory.createResource(NS + "autodelete");
	public static final Resource availability = ResourceFactory.createResource(NS + "availability");
	public static final Resource availabilityStatus = ResourceFactory.createResource(NS + "availabilityStatus");
	public static final Resource availableEndTime = ResourceFactory.createResource(NS + "availableEndTime");
	public static final Resource availableStartTime = ResourceFactory.createResource(NS + "availableStartTime");
	public static final Resource availableTime = ResourceFactory.createResource(NS + "availableTime");
	public static final Resource average = ResourceFactory.createResource(NS + "average");
	public static final Resource averageMolecularFormula = ResourceFactory
			.createResource(NS + "averageMolecularFormula");
	public static final Resource axis = ResourceFactory.createResource(NS + "axis");
	public static final Resource backCurve = ResourceFactory.createResource(NS + "backCurve");
	public static final Resource balance = ResourceFactory.createResource(NS + "balance");
	public static final Resource base = ResourceFactory.createResource(NS + "base");
	public static final Resource base64Binary = ResourceFactory.createResource(NS + "base64Binary");
	public static final Resource baseCitation = ResourceFactory.createResource(NS + "baseCitation");
	public static final Resource baseDefinition = ResourceFactory.createResource(NS + "baseDefinition");
	public static final Resource baseFormulaProductName = ResourceFactory.createResource(NS + "baseFormulaProductName");
	public static final Resource baseFormulaType = ResourceFactory.createResource(NS + "baseFormulaType");
	public static final Resource baseUnit = ResourceFactory.createResource(NS + "baseUnit");
	public static final Resource basedOn = ResourceFactory.createResource(NS + "basedOn");
	public static final Resource basis = ResourceFactory.createResource(NS + "basis");
	public static final Resource batch = ResourceFactory.createResource(NS + "batch");
	public static final Resource beneficiary = ResourceFactory.createResource(NS + "beneficiary");
	public static final Resource benefit = ResourceFactory.createResource(NS + "benefit");
	public static final Resource benefitBalance = ResourceFactory.createResource(NS + "benefitBalance");
	public static final Resource benefitPeriod = ResourceFactory.createResource(NS + "benefitPeriod");
	public static final Resource billablePeriod = ResourceFactory.createResource(NS + "billablePeriod");
	public static final Resource billingStatus = ResourceFactory.createResource(NS + "billingStatus");
	public static final Resource binding = ResourceFactory.createResource(NS + "binding");
	public static final Resource biologicalSourceEvent = ResourceFactory.createResource(NS + "biologicalSourceEvent");
	public static final Resource birthDate = ResourceFactory.createResource(NS + "birthDate");
	public static final Resource bodyLandmarkOrientation = ResourceFactory
			.createResource(NS + "bodyLandmarkOrientation");
	public static final Resource bodySite = ResourceFactory.createResource(NS + "bodySite");
	public static final Resource bodyStructure = ResourceFactory.createResource(NS + "bodyStructure");
	public static final Resource bodysite = ResourceFactory.createResource(NS + "bodysite");
	public static final Resource fhir_boolean = ResourceFactory.createResource(NS + "boolean");
	public static final Resource born = ResourceFactory.createResource(NS + "born");
	public static final Resource bounds = ResourceFactory.createResource(NS + "bounds");
	public static final Resource brand = ResourceFactory.createResource(NS + "brand");
	public static final Resource businessArrangement = ResourceFactory.createResource(NS + "businessArrangement");
	public static final Resource businessStatus = ResourceFactory.createResource(NS + "businessStatus");
	public static final Resource cTerminalModification = ResourceFactory.createResource(NS + "cTerminalModification");
	public static final Resource cTerminalModificationId = ResourceFactory
			.createResource(NS + "cTerminalModificationId");
	public static final Resource calculatedAt = ResourceFactory.createResource(NS + "calculatedAt");
	public static final Resource calibration = ResourceFactory.createResource(NS + "calibration");
	public static final Resource caloricDensity = ResourceFactory.createResource(NS + "caloricDensity");
	public static final Resource canFilterBy = ResourceFactory.createResource(NS + "canFilterBy");
	public static final Resource canPushUpdates = ResourceFactory.createResource(NS + "canPushUpdates");
	public static final Resource cancellationDate = ResourceFactory.createResource(NS + "cancellationDate");
	public static final Resource cancellationReason = ResourceFactory.createResource(NS + "cancellationReason");
	public static final Resource cancelledReason = ResourceFactory.createResource(NS + "cancelledReason");
	public static final Resource candidate = ResourceFactory.createResource(NS + "candidate");
	public static final Resource canonical = ResourceFactory.createResource(NS + "canonical");
	public static final Resource cap = ResourceFactory.createResource(NS + "cap");
	public static final Resource capabilities = ResourceFactory.createResource(NS + "capabilities");
	public static final Resource capability = ResourceFactory.createResource(NS + "capability");
	public static final Resource capacity = ResourceFactory.createResource(NS + "capacity");
	public static final Resource cardBrand = ResourceFactory.createResource(NS + "cardBrand");
	public static final Resource cardinalityBehavior = ResourceFactory.createResource(NS + "cardinalityBehavior");
	public static final Resource careManager = ResourceFactory.createResource(NS + "careManager");
	public static final Resource careTeam = ResourceFactory.createResource(NS + "careTeam");
	public static final Resource careTeamSequence = ResourceFactory.createResource(NS + "careTeamSequence");
	public static final Resource carrierAIDC = ResourceFactory.createResource(NS + "carrierAIDC");
	public static final Resource carrierHRF = ResourceFactory.createResource(NS + "carrierHRF");
	public static final Resource fhir_case = ResourceFactory.createResource(NS + "case");
	public static final Resource caseSensitive = ResourceFactory.createResource(NS + "caseSensitive");
	public static final Resource category = ResourceFactory.createResource(NS + "category");
	public static final Resource causality = ResourceFactory.createResource(NS + "causality");
	public static final Resource cause = ResourceFactory.createResource(NS + "cause");
	public static final Resource certainty = ResourceFactory.createResource(NS + "certainty");
	public static final Resource chain = ResourceFactory.createResource(NS + "chain");
	public static final Resource changePattern = ResourceFactory.createResource(NS + "changePattern");
	public static final Resource changeType = ResourceFactory.createResource(NS + "changeType");
	public static final Resource channelType = ResourceFactory.createResource(NS + "channelType");
	public static final Resource characteristic = ResourceFactory.createResource(NS + "characteristic");
	public static final Resource characteristicType = ResourceFactory.createResource(NS + "characteristicType");
	public static final Resource characterization = ResourceFactory.createResource(NS + "characterization");
	public static final Resource chargeItem = ResourceFactory.createResource(NS + "chargeItem");
	public static final Resource chargeItemCode = ResourceFactory.createResource(NS + "chargeItemCode");
	public static final Resource check = ResourceFactory.createResource(NS + "check");
	public static final Resource chromosome = ResourceFactory.createResource(NS + "chromosome");
	public static final Resource citation = ResourceFactory.createResource(NS + "citation");
	public static final Resource citeAs = ResourceFactory.createResource(NS + "citeAs");
	public static final Resource citedArtifact = ResourceFactory.createResource(NS + "citedArtifact");
	public static final Resource citedMedium = ResourceFactory.createResource(NS + "citedMedium");
	public static final Resource city = ResourceFactory.createResource(NS + "city");
	public static final Resource claim = ResourceFactory.createResource(NS + "claim");
	public static final Resource claimResponse = ResourceFactory.createResource(NS + "claimResponse");
	public static final Resource fhir_class = ResourceFactory.createResource(NS + "class");
	public static final Resource classification = ResourceFactory.createResource(NS + "classification");
	public static final Resource classifier = ResourceFactory.createResource(NS + "classifier");
	public static final Resource clinicalRecommendationStatement = ResourceFactory
			.createResource(NS + "clinicalRecommendationStatement");
	public static final Resource clinicalStatus = ResourceFactory.createResource(NS + "clinicalStatus");
	public static final Resource clinicalTrial = ResourceFactory.createResource(NS + "clinicalTrial");
	public static final Resource clinicalUseIssue = ResourceFactory.createResource(NS + "clinicalUseIssue");
	public static final Resource clockFacePosition = ResourceFactory.createResource(NS + "clockFacePosition");
	public static final Resource closure = ResourceFactory.createResource(NS + "closure");
	public static final Resource code = ResourceFactory.createResource(NS + "code");
	public static final Resource codeFilter = ResourceFactory.createResource(NS + "codeFilter");
	public static final Resource codeMap = ResourceFactory.createResource(NS + "codeMap");
	public static final Resource codeSearch = ResourceFactory.createResource(NS + "codeSearch");
	public static final Resource codeSystem = ResourceFactory.createResource(NS + "codeSystem");
	public static final Resource coding = ResourceFactory.createResource(NS + "coding");
	public static final Resource collected = ResourceFactory.createResource(NS + "collected");
	public static final Resource collection = ResourceFactory.createResource(NS + "collection");
	public static final Resource collector = ResourceFactory.createResource(NS + "collector");
	public static final Resource color = ResourceFactory.createResource(NS + "color");
	public static final Resource combined = ResourceFactory.createResource(NS + "combined");
	public static final Resource combinedPharmaceuticalDoseForm = ResourceFactory
			.createResource(NS + "combinedPharmaceuticalDoseForm");
	public static final Resource combining = ResourceFactory.createResource(NS + "combining");
	public static final Resource comment = ResourceFactory.createResource(NS + "comment");
	public static final Resource communication = ResourceFactory.createResource(NS + "communication");
	public static final Resource communicationMethod = ResourceFactory.createResource(NS + "communicationMethod");
	public static final Resource communicationRequest = ResourceFactory.createResource(NS + "communicationRequest");
	public static final Resource comorbidity = ResourceFactory.createResource(NS + "comorbidity");
	public static final Resource comparator = ResourceFactory.createResource(NS + "comparator");
	public static final Resource compareToSourceExpression = ResourceFactory
			.createResource(NS + "compareToSourceExpression");
	public static final Resource compareToSourceId = ResourceFactory.createResource(NS + "compareToSourceId");
	public static final Resource compareToSourcePath = ResourceFactory.createResource(NS + "compareToSourcePath");
	public static final Resource comparisonGroup = ResourceFactory.createResource(NS + "comparisonGroup");
	public static final Resource compartment = ResourceFactory.createResource(NS + "compartment");
	public static final Resource complete = ResourceFactory.createResource(NS + "complete");
	public static final Resource completionTime = ResourceFactory.createResource(NS + "completionTime");
	public static final Resource complication = ResourceFactory.createResource(NS + "complication");
	public static final Resource component = ResourceFactory.createResource(NS + "component");
	public static final Resource componentPart = ResourceFactory.createResource(NS + "componentPart");
	public static final Resource compose = ResourceFactory.createResource(NS + "compose");
	public static final Resource compositeScoring = ResourceFactory.createResource(NS + "compositeScoring");
	public static final Resource composition = ResourceFactory.createResource(NS + "composition");
	public static final Resource compositional = ResourceFactory.createResource(NS + "compositional");
	public static final Resource comprisedOf = ResourceFactory.createResource(NS + "comprisedOf");
	public static final Resource concentration = ResourceFactory.createResource(NS + "concentration");
	public static final Resource concept = ResourceFactory.createResource(NS + "concept");
	public static final Resource conclusion = ResourceFactory.createResource(NS + "conclusion");
	public static final Resource conclusionCode = ResourceFactory.createResource(NS + "conclusionCode");
	public static final Resource condition = ResourceFactory.createResource(NS + "condition");
	public static final Resource conditionalCreate = ResourceFactory.createResource(NS + "conditionalCreate");
	public static final Resource conditionalDelete = ResourceFactory.createResource(NS + "conditionalDelete");
	public static final Resource conditionalPatch = ResourceFactory.createResource(NS + "conditionalPatch");
	public static final Resource conditionalRead = ResourceFactory.createResource(NS + "conditionalRead");
	public static final Resource conditionalUpdate = ResourceFactory.createResource(NS + "conditionalUpdate");
	public static final Resource conditionality = ResourceFactory.createResource(NS + "conditionality");
	public static final Resource confidentialityIndicator = ResourceFactory
			.createResource(NS + "confidentialityIndicator");
	public static final Resource conformance = ResourceFactory.createResource(NS + "conformance");
	public static final Resource conformsTo = ResourceFactory.createResource(NS + "conformsTo");
	public static final Resource connectionType = ResourceFactory.createResource(NS + "connectionType");
	public static final Resource connectivity = ResourceFactory.createResource(NS + "connectivity");
	public static final Resource consent = ResourceFactory.createResource(NS + "consent");
	public static final Resource fhir_const = ResourceFactory.createResource(NS + "const");
	public static final Resource constituent = ResourceFactory.createResource(NS + "constituent");
	public static final Resource constraint = ResourceFactory.createResource(NS + "constraint");
	public static final Resource consumedItem = ResourceFactory.createResource(NS + "consumedItem");
	public static final Resource contact = ResourceFactory.createResource(NS + "contact");
	public static final Resource contained = ResourceFactory.createResource(NS + "contained");
	public static final Resource containedInstance = ResourceFactory.createResource(NS + "containedInstance");
	public static final Resource containedItem = ResourceFactory.createResource(NS + "containedItem");
	public static final Resource containedItemQuantity = ResourceFactory.createResource(NS + "containedItemQuantity");
	public static final Resource container = ResourceFactory.createResource(NS + "container");
	public static final Resource contains = ResourceFactory.createResource(NS + "contains");
	public static final Resource content = ResourceFactory.createResource(NS + "content");
	public static final Resource contentDefinition = ResourceFactory.createResource(NS + "contentDefinition");
	public static final Resource contentDerivative = ResourceFactory.createResource(NS + "contentDerivative");
	public static final Resource contentReference = ResourceFactory.createResource(NS + "contentReference");
	public static final Resource contentType = ResourceFactory.createResource(NS + "contentType");
	public static final Resource context = ResourceFactory.createResource(NS + "context");
	public static final Resource contextInvariant = ResourceFactory.createResource(NS + "contextInvariant");
	public static final Resource contextLinkId = ResourceFactory.createResource(NS + "contextLinkId");
	public static final Resource continuous = ResourceFactory.createResource(NS + "continuous");
	public static final Resource contract = ResourceFactory.createResource(NS + "contract");
	public static final Resource contraindicatedVaccineCode = ResourceFactory
			.createResource(NS + "contraindicatedVaccineCode");
	public static final Resource contraindication = ResourceFactory.createResource(NS + "contraindication");
	public static final Resource contributedToDeath = ResourceFactory.createResource(NS + "contributedToDeath");
	public static final Resource contributingFactor = ResourceFactory.createResource(NS + "contributingFactor");
	public static final Resource contributionInstance = ResourceFactory.createResource(NS + "contributionInstance");
	public static final Resource contributionType = ResourceFactory.createResource(NS + "contributionType");
	public static final Resource contributor = ResourceFactory.createResource(NS + "contributor");
	public static final Resource contributorship = ResourceFactory.createResource(NS + "contributorship");
	public static final Resource control = ResourceFactory.createResource(NS + "control");
	public static final Resource controller = ResourceFactory.createResource(NS + "controller");
	public static final Resource coordinate = ResourceFactory.createResource(NS + "coordinate");
	public static final Resource coordinateSystem = ResourceFactory.createResource(NS + "coordinateSystem");
	public static final Resource copackagedIndicator = ResourceFactory.createResource(NS + "copackagedIndicator");
	public static final Resource copolymerConnectivity = ResourceFactory.createResource(NS + "copolymerConnectivity");
	public static final Resource copyright = ResourceFactory.createResource(NS + "copyright");
	public static final Resource copyrightLabel = ResourceFactory.createResource(NS + "copyrightLabel");
	public static final Resource correctiveAction = ResourceFactory.createResource(NS + "correctiveAction");
	public static final Resource correspondingContact = ResourceFactory.createResource(NS + "correspondingContact");
	public static final Resource cors = ResourceFactory.createResource(NS + "cors");
	public static final Resource cost = ResourceFactory.createResource(NS + "cost");
	public static final Resource costCenter = ResourceFactory.createResource(NS + "costCenter");
	public static final Resource costToBeneficiary = ResourceFactory.createResource(NS + "costToBeneficiary");
	public static final Resource count = ResourceFactory.createResource(NS + "count");
	public static final Resource countMax = ResourceFactory.createResource(NS + "countMax");
	public static final Resource countType = ResourceFactory.createResource(NS + "countType");
	public static final Resource countingDateTime = ResourceFactory.createResource(NS + "countingDateTime");
	public static final Resource country = ResourceFactory.createResource(NS + "country");
	public static final Resource countryOfOrigin = ResourceFactory.createResource(NS + "countryOfOrigin");
	public static final Resource courseOfTherapyType = ResourceFactory.createResource(NS + "courseOfTherapyType");
	public static final Resource coverage = ResourceFactory.createResource(NS + "coverage");
	public static final Resource coverageArea = ResourceFactory.createResource(NS + "coverageArea");
	public static final Resource covers = ResourceFactory.createResource(NS + "covers");
	public static final Resource created = ResourceFactory.createResource(NS + "created");
	public static final Resource creation = ResourceFactory.createResource(NS + "creation");
	public static final Resource criteria = ResourceFactory.createResource(NS + "criteria");
	public static final Resource criticalCodedValueSet = ResourceFactory.createResource(NS + "criticalCodedValueSet");
	public static final Resource criticality = ResourceFactory.createResource(NS + "criticality");
	public static final Resource crossReference = ResourceFactory.createResource(NS + "crossReference");
	public static final Resource currency = ResourceFactory.createResource(NS + "currency");
	public static final Resource current = ResourceFactory.createResource(NS + "current");
	public static final Resource currentLocation = ResourceFactory.createResource(NS + "currentLocation");
	public static final Resource currentState = ResourceFactory.createResource(NS + "currentState");
	public static final Resource custodian = ResourceFactory.createResource(NS + "custodian");
	public static final Resource cycle = ResourceFactory.createResource(NS + "cycle");
	public static final Resource cylinder = ResourceFactory.createResource(NS + "cylinder");
	public static final Resource data = ResourceFactory.createResource(NS + "data");
	public static final Resource dataAbsentReason = ResourceFactory.createResource(NS + "dataAbsentReason");
	public static final Resource dataPeriod = ResourceFactory.createResource(NS + "dataPeriod");
	public static final Resource dataRequirement = ResourceFactory.createResource(NS + "dataRequirement");
	public static final Resource dataUpdateType = ResourceFactory.createResource(NS + "dataUpdateType");
	public static final Resource date = ResourceFactory.createResource(NS + "date");
	public static final Resource dateAccessed = ResourceFactory.createResource(NS + "dateAccessed");
	public static final Resource dateAsserted = ResourceFactory.createResource(NS + "dateAsserted");
	public static final Resource dateCriterion = ResourceFactory.createResource(NS + "dateCriterion");
	public static final Resource dateFilter = ResourceFactory.createResource(NS + "dateFilter");
	public static final Resource dateOfDiagnosis = ResourceFactory.createResource(NS + "dateOfDiagnosis");
	public static final Resource dateOfService = ResourceFactory.createResource(NS + "dateOfService");
	public static final Resource dateRange = ResourceFactory.createResource(NS + "dateRange");
	public static final Resource dateTime = ResourceFactory.createResource(NS + "dateTime");
	public static final Resource dateWritten = ResourceFactory.createResource(NS + "dateWritten");
	public static final Resource dayOfMonth = ResourceFactory.createResource(NS + "dayOfMonth");
	public static final Resource dayOfWeek = ResourceFactory.createResource(NS + "dayOfWeek");
	public static final Resource daysOfWeek = ResourceFactory.createResource(NS + "daysOfWeek");
	public static final Resource daysSupply = ResourceFactory.createResource(NS + "daysSupply");
	public static final Resource deceased = ResourceFactory.createResource(NS + "deceased");
	public static final Resource decimal = ResourceFactory.createResource(NS + "decimal");
	public static final Resource decision = ResourceFactory.createResource(NS + "decision");
	public static final Resource decisionMode = ResourceFactory.createResource(NS + "decisionMode");
	public static final Resource defaultManualCompletion = ResourceFactory
			.createResource(NS + "defaultManualCompletion");
	public static final Resource defaultValue = ResourceFactory.createResource(NS + "defaultValue");
	public static final Resource definition = ResourceFactory.createResource(NS + "definition");
	public static final Resource definitionByCombination = ResourceFactory
			.createResource(NS + "definitionByCombination");
	public static final Resource definitionByTypeAndValue = ResourceFactory
			.createResource(NS + "definitionByTypeAndValue");
	public static final Resource definitionCanonical = ResourceFactory.createResource(NS + "definitionCanonical");
	public static final Resource definitionCodeableConcept = ResourceFactory
			.createResource(NS + "definitionCodeableConcept");
	public static final Resource definitionExpression = ResourceFactory.createResource(NS + "definitionExpression");
	public static final Resource definitionId = ResourceFactory.createResource(NS + "definitionId");
	public static final Resource definitionReference = ResourceFactory.createResource(NS + "definitionReference");
	public static final Resource definitionUri = ResourceFactory.createResource(NS + "definitionUri");
	public static final Resource definitional = ResourceFactory.createResource(NS + "definitional");
	public static final Resource degreeOfPolymerisation = ResourceFactory.createResource(NS + "degreeOfPolymerisation");
	public static final Resource deleted = ResourceFactory.createResource(NS + "deleted");
	public static final Resource deliverFor = ResourceFactory.createResource(NS + "deliverFor");
	public static final Resource deliverFrom = ResourceFactory.createResource(NS + "deliverFrom");
	public static final Resource deliverTo = ResourceFactory.createResource(NS + "deliverTo");
	public static final Resource deliveryDevice = ResourceFactory.createResource(NS + "deliveryDevice");
	public static final Resource denominator = ResourceFactory.createResource(NS + "denominator");
	public static final Resource dependency = ResourceFactory.createResource(NS + "dependency");
	public static final Resource dependent = ResourceFactory.createResource(NS + "dependent");
	public static final Resource dependsOn = ResourceFactory.createResource(NS + "dependsOn");
	public static final Resource derivation = ResourceFactory.createResource(NS + "derivation");
	public static final Resource derivedFrom = ResourceFactory.createResource(NS + "derivedFrom");
	public static final Resource derivedFromCanonical = ResourceFactory.createResource(NS + "derivedFromCanonical");
	public static final Resource derivedFromUri = ResourceFactory.createResource(NS + "derivedFromUri");
	public static final Resource description = ResourceFactory.createResource(NS + "description");
	public static final Resource descriptionSummary = ResourceFactory.createResource(NS + "descriptionSummary");
	public static final Resource designation = ResourceFactory.createResource(NS + "designation");
	public static final Resource destination = ResourceFactory.createResource(NS + "destination");
	public static final Resource detail = ResourceFactory.createResource(NS + "detail");
	public static final Resource detailSequence = ResourceFactory.createResource(NS + "detailSequence");
	public static final Resource details = ResourceFactory.createResource(NS + "details");
	public static final Resource detected = ResourceFactory.createResource(NS + "detected");
	public static final Resource developmentStage = ResourceFactory.createResource(NS + "developmentStage");
	public static final Resource device = ResourceFactory.createResource(NS + "device");
	public static final Resource deviceIdentifier = ResourceFactory.createResource(NS + "deviceIdentifier");
	public static final Resource deviceName = ResourceFactory.createResource(NS + "deviceName");
	public static final Resource diagnosis = ResourceFactory.createResource(NS + "diagnosis");
	public static final Resource diagnosisRelatedGroup = ResourceFactory.createResource(NS + "diagnosisRelatedGroup");
	public static final Resource diagnosisSequence = ResourceFactory.createResource(NS + "diagnosisSequence");
	public static final Resource diagnostics = ResourceFactory.createResource(NS + "diagnostics");
	public static final Resource diameter = ResourceFactory.createResource(NS + "diameter");
	public static final Resource dietPreference = ResourceFactory.createResource(NS + "dietPreference");
	public static final Resource differential = ResourceFactory.createResource(NS + "differential");
	public static final Resource dimensions = ResourceFactory.createResource(NS + "dimensions");
	public static final Resource direction = ResourceFactory.createResource(NS + "direction");
	public static final Resource directnessMatch = ResourceFactory.createResource(NS + "directnessMatch");
	public static final Resource disabledDisplay = ResourceFactory.createResource(NS + "disabledDisplay");
	public static final Resource dischargeDisposition = ResourceFactory.createResource(NS + "dischargeDisposition");
	public static final Resource disclaimer = ResourceFactory.createResource(NS + "disclaimer");
	public static final Resource discriminator = ResourceFactory.createResource(NS + "discriminator");
	public static final Resource diseaseStatus = ResourceFactory.createResource(NS + "diseaseStatus");
	public static final Resource diseaseSymptomProcedure = ResourceFactory
			.createResource(NS + "diseaseSymptomProcedure");
	public static final Resource dispenseInterval = ResourceFactory.createResource(NS + "dispenseInterval");
	public static final Resource dispenseRequest = ResourceFactory.createResource(NS + "dispenseRequest");
	public static final Resource dispenser = ResourceFactory.createResource(NS + "dispenser");
	public static final Resource dispenserInstruction = ResourceFactory.createResource(NS + "dispenserInstruction");
	public static final Resource display = ResourceFactory.createResource(NS + "display");
	public static final Resource displayName = ResourceFactory.createResource(NS + "displayName");
	public static final Resource disposition = ResourceFactory.createResource(NS + "disposition");
	public static final Resource distanceFromLandmark = ResourceFactory.createResource(NS + "distanceFromLandmark");
	public static final Resource distributor = ResourceFactory.createResource(NS + "distributor");
	public static final Resource district = ResourceFactory.createResource(NS + "district");
	public static final Resource disulfideLinkage = ResourceFactory.createResource(NS + "disulfideLinkage");
	public static final Resource div = ResourceFactory.createResource(NS + "div");
	public static final Resource division = ResourceFactory.createResource(NS + "division");
	public static final Resource doNotPerform = ResourceFactory.createResource(NS + "doNotPerform");
	public static final Resource docStatus = ResourceFactory.createResource(NS + "docStatus");
	public static final Resource document = ResourceFactory.createResource(NS + "document");
	public static final Resource documentType = ResourceFactory.createResource(NS + "documentType");
	public static final Resource documentation = ResourceFactory.createResource(NS + "documentation");
	public static final Resource domain = ResourceFactory.createResource(NS + "domain");
	public static final Resource dosage = ResourceFactory.createResource(NS + "dosage");
	public static final Resource dosageInstruction = ResourceFactory.createResource(NS + "dosageInstruction");
	public static final Resource dose = ResourceFactory.createResource(NS + "dose");
	public static final Resource doseAdministrationAid = ResourceFactory.createResource(NS + "doseAdministrationAid");
	public static final Resource doseAndRate = ResourceFactory.createResource(NS + "doseAndRate");
	public static final Resource doseForm = ResourceFactory.createResource(NS + "doseForm");
	public static final Resource doseNumber = ResourceFactory.createResource(NS + "doseNumber");
	public static final Resource doseQuantity = ResourceFactory.createResource(NS + "doseQuantity");
	public static final Resource doseStatus = ResourceFactory.createResource(NS + "doseStatus");
	public static final Resource doseStatusReason = ResourceFactory.createResource(NS + "doseStatusReason");
	public static final Resource dosingGuideline = ResourceFactory.createResource(NS + "dosingGuideline");
	public static final Resource drugCharacteristic = ResourceFactory.createResource(NS + "drugCharacteristic");
	public static final Resource due = ResourceFactory.createResource(NS + "due");
	public static final Resource duration = ResourceFactory.createResource(NS + "duration");
	public static final Resource durationMax = ResourceFactory.createResource(NS + "durationMax");
	public static final Resource durationUnit = ResourceFactory.createResource(NS + "durationUnit");
	public static final Resource during = ResourceFactory.createResource(NS + "during");
	public static final Resource dynamicValue = ResourceFactory.createResource(NS + "dynamicValue");
	public static final Resource edit = ResourceFactory.createResource(NS + "edit");
	public static final Resource editor = ResourceFactory.createResource(NS + "editor");
	public static final Resource effect = ResourceFactory.createResource(NS + "effect");
	public static final Resource effective = ResourceFactory.createResource(NS + "effective");
	public static final Resource effectiveDate = ResourceFactory.createResource(NS + "effectiveDate");
	public static final Resource effectiveDosePeriod = ResourceFactory.createResource(NS + "effectiveDosePeriod");
	public static final Resource effectivePeriod = ResourceFactory.createResource(NS + "effectivePeriod");
	public static final Resource effectiveTime = ResourceFactory.createResource(NS + "effectiveTime");
	public static final Resource element = ResourceFactory.createResource(NS + "element");
	public static final Resource eligibility = ResourceFactory.createResource(NS + "eligibility");
	public static final Resource emptyReason = ResourceFactory.createResource(NS + "emptyReason");
	public static final Resource enableBehavior = ResourceFactory.createResource(NS + "enableBehavior");
	public static final Resource enableWhen = ResourceFactory.createResource(NS + "enableWhen");
	public static final Resource encodeRequestUrl = ResourceFactory.createResource(NS + "encodeRequestUrl");
	public static final Resource encounter = ResourceFactory.createResource(NS + "encounter");
	public static final Resource end = ResourceFactory.createResource(NS + "end");
	public static final Resource endDate = ResourceFactory.createResource(NS + "endDate");
	public static final Resource endParam = ResourceFactory.createResource(NS + "endParam");
	public static final Resource endRelationship = ResourceFactory.createResource(NS + "endRelationship");
	public static final Resource endorser = ResourceFactory.createResource(NS + "endorser");
	public static final Resource endpoint = ResourceFactory.createResource(NS + "endpoint");
	public static final Resource enteralFormula = ResourceFactory.createResource(NS + "enteralFormula");
	public static final Resource enteredDate = ResourceFactory.createResource(NS + "enteredDate");
	public static final Resource enterer = ResourceFactory.createResource(NS + "enterer");
	public static final Resource entity = ResourceFactory.createResource(NS + "entity");
	public static final Resource entityRelatedness = ResourceFactory.createResource(NS + "entityRelatedness");
	public static final Resource entry = ResourceFactory.createResource(NS + "entry");
	public static final Resource entryClassifier = ResourceFactory.createResource(NS + "entryClassifier");
	public static final Resource entryQuantity = ResourceFactory.createResource(NS + "entryQuantity");
	public static final Resource entryReference = ResourceFactory.createResource(NS + "entryReference");
	public static final Resource entryType = ResourceFactory.createResource(NS + "entryType");
	public static final Resource environmentType = ResourceFactory.createResource(NS + "environmentType");
	public static final Resource environmentalSetting = ResourceFactory.createResource(NS + "environmentalSetting");
	public static final Resource episodeOfCare = ResourceFactory.createResource(NS + "episodeOfCare");
	public static final Resource error = ResourceFactory.createResource(NS + "error");
	public static final Resource estimate = ResourceFactory.createResource(NS + "estimate");
	public static final Resource estimatedAge = ResourceFactory.createResource(NS + "estimatedAge");
	public static final Resource etag = ResourceFactory.createResource(NS + "etag");
	public static final Resource evaluatedResource = ResourceFactory.createResource(NS + "evaluatedResource");
	public static final Resource evaluationMessage = ResourceFactory.createResource(NS + "evaluationMessage");
	public static final Resource event = ResourceFactory.createResource(NS + "event");
	public static final Resource eventHistory = ResourceFactory.createResource(NS + "eventHistory");
	public static final Resource eventNumber = ResourceFactory.createResource(NS + "eventNumber");
	public static final Resource eventTrigger = ResourceFactory.createResource(NS + "eventTrigger");
	public static final Resource eventsSinceSubscriptionStart = ResourceFactory
			.createResource(NS + "eventsSinceSubscriptionStart");
	public static final Resource evidence = ResourceFactory.createResource(NS + "evidence");
	public static final Resource example = ResourceFactory.createResource(NS + "example");
	public static final Resource exception = ResourceFactory.createResource(NS + "exception");
	public static final Resource exclude = ResourceFactory.createResource(NS + "exclude");
	public static final Resource excludeFoodModifier = ResourceFactory.createResource(NS + "excludeFoodModifier");
	public static final Resource excluded = ResourceFactory.createResource(NS + "excluded");
	public static final Resource excludedStructure = ResourceFactory.createResource(NS + "excludedStructure");
	public static final Resource excludingDate = ResourceFactory.createResource(NS + "excludingDate");
	public static final Resource excludingRecurrenceId = ResourceFactory.createResource(NS + "excludingRecurrenceId");
	public static final Resource exclusionCriteria = ResourceFactory.createResource(NS + "exclusionCriteria");
	public static final Resource executionPeriod = ResourceFactory.createResource(NS + "executionPeriod");
	public static final Resource exitCriteria = ResourceFactory.createResource(NS + "exitCriteria");
	public static final Resource expansion = ResourceFactory.createResource(NS + "expansion");
	public static final Resource expectedInResearchStudy = ResourceFactory
			.createResource(NS + "expectedInResearchStudy");
	public static final Resource expectedSupplyDuration = ResourceFactory.createResource(NS + "expectedSupplyDuration");
	public static final Resource experimental = ResourceFactory.createResource(NS + "experimental");
	public static final Resource expirationDate = ResourceFactory.createResource(NS + "expirationDate");
	public static final Resource expirationType = ResourceFactory.createResource(NS + "expirationType");
	public static final Resource expiry = ResourceFactory.createResource(NS + "expiry");
	public static final Resource exposureRoute = ResourceFactory.createResource(NS + "exposureRoute");
	public static final Resource expression = ResourceFactory.createResource(NS + "expression");
	public static final Resource fhir_extends = ResourceFactory.createResource(NS + "extends");
	public static final Resource extension = ResourceFactory.createResource(NS + "extension");
	public static final Resource extraDetails = ResourceFactory.createResource(NS + "extraDetails");
	public static final Resource eye = ResourceFactory.createResource(NS + "eye");
	public static final Resource facility = ResourceFactory.createResource(NS + "facility");
	public static final Resource facilityType = ResourceFactory.createResource(NS + "facilityType");
	public static final Resource factor = ResourceFactory.createResource(NS + "factor");
	public static final Resource failureAction = ResourceFactory.createResource(NS + "failureAction");
	public static final Resource family = ResourceFactory.createResource(NS + "family");
	public static final Resource fastingStatus = ResourceFactory.createResource(NS + "fastingStatus");
	public static final Resource feature = ResourceFactory.createResource(NS + "feature");
	public static final Resource fhir_ttl = ResourceFactory.createResource(NS + "fhir.ttl");
	public static final Resource fhirPathCriteria = ResourceFactory.createResource(NS + "fhirPathCriteria");
	public static final Resource fhirVersion = ResourceFactory.createResource(NS + "fhirVersion");
	public static final Resource field = ResourceFactory.createResource(NS + "field");
	public static final Resource file = ResourceFactory.createResource(NS + "file");
	public static final Resource filter = ResourceFactory.createResource(NS + "filter");
	public static final Resource filterBy = ResourceFactory.createResource(NS + "filterBy");
	public static final Resource filterDefinition = ResourceFactory.createResource(NS + "filterDefinition");
	public static final Resource filterParameter = ResourceFactory.createResource(NS + "filterParameter");
	public static final Resource financial = ResourceFactory.createResource(NS + "financial");
	public static final Resource finding = ResourceFactory.createResource(NS + "finding");
	public static final Resource firstDose = ResourceFactory.createResource(NS + "firstDose");
	public static final Resource firstPage = ResourceFactory.createResource(NS + "firstPage");
	public static final Resource fivePrime = ResourceFactory.createResource(NS + "fivePrime");
	public static final Resource fixed = ResourceFactory.createResource(NS + "fixed");
	public static final Resource fixture = ResourceFactory.createResource(NS + "fixture");
	public static final Resource flag = ResourceFactory.createResource(NS + "flag");
	public static final Resource fluidConsistencyType = ResourceFactory.createResource(NS + "fluidConsistencyType");
	public static final Resource focal = ResourceFactory.createResource(NS + "focal");
	public static final Resource focalDevice = ResourceFactory.createResource(NS + "focalDevice");
	public static final Resource focus = ResourceFactory.createResource(NS + "focus");
	public static final Resource focusReference = ResourceFactory.createResource(NS + "focusReference");
	public static final Resource followUp = ResourceFactory.createResource(NS + "followUp");
	public static final Resource foodPreferenceModifier = ResourceFactory.createResource(NS + "foodPreferenceModifier");
	public static final Resource foodType = ResourceFactory.createResource(NS + "foodType");
	public static final Resource fhir_for = ResourceFactory.createResource(NS + "for");
	public static final Resource forecastReason = ResourceFactory.createResource(NS + "forecastReason");
	public static final Resource forecastStatus = ResourceFactory.createResource(NS + "forecastStatus");
	public static final Resource forenameInitials = ResourceFactory.createResource(NS + "forenameInitials");
	public static final Resource form = ResourceFactory.createResource(NS + "form");
	public static final Resource formCode = ResourceFactory.createResource(NS + "formCode");
	public static final Resource formOf = ResourceFactory.createResource(NS + "formOf");
	public static final Resource format = ResourceFactory.createResource(NS + "format");
	public static final Resource formatted = ResourceFactory.createResource(NS + "formatted");
	public static final Resource fraction = ResourceFactory.createResource(NS + "fraction");
	public static final Resource fractionDescription = ResourceFactory.createResource(NS + "fractionDescription");
	public static final Resource frameOfReferenceUid = ResourceFactory.createResource(NS + "frameOfReferenceUid");
	public static final Resource frames = ResourceFactory.createResource(NS + "frames");
	public static final Resource freeToShare = ResourceFactory.createResource(NS + "freeToShare");
	public static final Resource frequency = ResourceFactory.createResource(NS + "frequency");
	public static final Resource frequencyMax = ResourceFactory.createResource(NS + "frequencyMax");
	public static final Resource frequencyOfOccurrence = ResourceFactory.createResource(NS + "frequencyOfOccurrence");
	public static final Resource friday = ResourceFactory.createResource(NS + "friday");
	public static final Resource friendly = ResourceFactory.createResource(NS + "friendly");
	public static final Resource fullUrl = ResourceFactory.createResource(NS + "fullUrl");
	public static final Resource function = ResourceFactory.createResource(NS + "function");
	public static final Resource fundingSource = ResourceFactory.createResource(NS + "fundingSource");
	public static final Resource fundsReserve = ResourceFactory.createResource(NS + "fundsReserve");
	public static final Resource fundsReserveRequested = ResourceFactory.createResource(NS + "fundsReserveRequested");
	public static final Resource gateway = ResourceFactory.createResource(NS + "gateway");
	public static final Resource gender = ResourceFactory.createResource(NS + "gender");
	public static final Resource gene = ResourceFactory.createResource(NS + "gene");
	public static final Resource geneElement = ResourceFactory.createResource(NS + "geneElement");
	public static final Resource geneSequenceOrigin = ResourceFactory.createResource(NS + "geneSequenceOrigin");
	public static final Resource generalCost = ResourceFactory.createResource(NS + "generalCost");
	public static final Resource generalPractitioner = ResourceFactory.createResource(NS + "generalPractitioner");
	public static final Resource generatedBy = ResourceFactory.createResource(NS + "generatedBy");
	public static final Resource generation = ResourceFactory.createResource(NS + "generation");
	public static final Resource genomeAssembly = ResourceFactory.createResource(NS + "genomeAssembly");
	public static final Resource genomeBuild = ResourceFactory.createResource(NS + "genomeBuild");
	public static final Resource genus = ResourceFactory.createResource(NS + "genus");
	public static final Resource geographicalLocation = ResourceFactory.createResource(NS + "geographicalLocation");
	public static final Resource geometry = ResourceFactory.createResource(NS + "geometry");
	public static final Resource gestationalAge = ResourceFactory.createResource(NS + "gestationalAge");
	public static final Resource given = ResourceFactory.createResource(NS + "given");
	public static final Resource global = ResourceFactory.createResource(NS + "global");
	public static final Resource goal = ResourceFactory.createResource(NS + "goal");
	public static final Resource goalId = ResourceFactory.createResource(NS + "goalId");
	public static final Resource grade = ResourceFactory.createResource(NS + "grade");
	public static final Resource grantee = ResourceFactory.createResource(NS + "grantee");
	public static final Resource grantor = ResourceFactory.createResource(NS + "grantor");
	public static final Resource graph = ResourceFactory.createResource(NS + "graph");
	public static final Resource group = ResourceFactory.createResource(NS + "group");
	public static final Resource groupDefinition = ResourceFactory.createResource(NS + "groupDefinition");
	public static final Resource groupIdentifier = ResourceFactory.createResource(NS + "groupIdentifier");
	public static final Resource groupSize = ResourceFactory.createResource(NS + "groupSize");
	public static final Resource grouping = ResourceFactory.createResource(NS + "grouping");
	public static final Resource groupingBehavior = ResourceFactory.createResource(NS + "groupingBehavior");
	public static final Resource groupingId = ResourceFactory.createResource(NS + "groupingId");
	public static final Resource guarantor = ResourceFactory.createResource(NS + "guarantor");
	public static final Resource guidance = ResourceFactory.createResource(NS + "guidance");
	public static final Resource guideline = ResourceFactory.createResource(NS + "guideline");
	public static final Resource handling = ResourceFactory.createResource(NS + "handling");
	public static final Resource hasBodySite = ResourceFactory.createResource(NS + "hasBodySite");
	public static final Resource hasIngredient = ResourceFactory.createResource(NS + "hasIngredient");
	public static final Resource hasMember = ResourceFactory.createResource(NS + "hasMember");
	public static final Resource hasPart = ResourceFactory.createResource(NS + "hasPart");
	public static final Resource hasSeverity = ResourceFactory.createResource(NS + "hasSeverity");
	public static final Resource hasStage = ResourceFactory.createResource(NS + "hasStage");
	public static final Resource hash = ResourceFactory.createResource(NS + "hash");
	public static final Resource header = ResourceFactory.createResource(NS + "header");
	public static final Resource headerField = ResourceFactory.createResource(NS + "headerField");
	public static final Resource healthcareService = ResourceFactory.createResource(NS + "healthcareService");
	public static final Resource heartbeatPeriod = ResourceFactory.createResource(NS + "heartbeatPeriod");
	public static final Resource height = ResourceFactory.createResource(NS + "height");
	public static final Resource hierarchical = ResourceFactory.createResource(NS + "hierarchical");
	public static final Resource hierarchyMeaning = ResourceFactory.createResource(NS + "hierarchyMeaning");
	public static final Resource high = ResourceFactory.createResource(NS + "high");
	public static final Resource highNumerator = ResourceFactory.createResource(NS + "highNumerator");
	public static final Resource hint = ResourceFactory.createResource(NS + "hint");
	public static final Resource history = ResourceFactory.createResource(NS + "history");
	public static final Resource holder = ResourceFactory.createResource(NS + "holder");
	public static final Resource hoursOfOperation = ResourceFactory.createResource(NS + "hoursOfOperation");
	public static final Resource human = ResourceFactory.createResource(NS + "human");
	public static final Resource hybrid = ResourceFactory.createResource(NS + "hybrid");
	public static final Resource hybridType = ResourceFactory.createResource(NS + "hybridType");
	public static final Resource id = ResourceFactory.createResource(NS + "id");
	public static final Resource identified = ResourceFactory.createResource(NS + "identified");
	public static final Resource identifier = ResourceFactory.createResource(NS + "identifier");
	public static final Resource identity = ResourceFactory.createResource(NS + "identity");
	public static final Resource identityCertificate = ResourceFactory.createResource(NS + "identityCertificate");
	public static final Resource ifMatch = ResourceFactory.createResource(NS + "ifMatch");
	public static final Resource ifModifiedSince = ResourceFactory.createResource(NS + "ifModifiedSince");
	public static final Resource ifNoneExist = ResourceFactory.createResource(NS + "ifNoneExist");
	public static final Resource ifNoneMatch = ResourceFactory.createResource(NS + "ifNoneMatch");
	public static final Resource image = ResourceFactory.createResource(NS + "image");
	public static final Resource imageRegion2D = ResourceFactory.createResource(NS + "imageRegion2D");
	public static final Resource imageRegion3D = ResourceFactory.createResource(NS + "imageRegion3D");
	public static final Resource immunizationEvent = ResourceFactory.createResource(NS + "immunizationEvent");
	public static final Resource immutable = ResourceFactory.createResource(NS + "immutable");
	public static final Resource implementation = ResourceFactory.createResource(NS + "implementation");
	public static final Resource implementationGuide = ResourceFactory.createResource(NS + "implementationGuide");
	public static final Resource implicated = ResourceFactory.createResource(NS + "implicated");
	public static final Resource implicitRules = ResourceFactory.createResource(NS + "implicitRules");
	public static final Resource fhir_import = ResourceFactory.createResource(NS + "import");
	public static final Resource imports = ResourceFactory.createResource(NS + "imports");
	public static final Resource improvementNotation = ResourceFactory.createResource(NS + "improvementNotation");
	public static final Resource impurity = ResourceFactory.createResource(NS + "impurity");
	public static final Resource inResponseTo = ResourceFactory.createResource(NS + "inResponseTo");
	public static final Resource inactive = ResourceFactory.createResource(NS + "inactive");
	public static final Resource incidence = ResourceFactory.createResource(NS + "incidence");
	public static final Resource include = ResourceFactory.createResource(NS + "include");
	public static final Resource includedStructure = ResourceFactory.createResource(NS + "includedStructure");
	public static final Resource inclusionCriteria = ResourceFactory.createResource(NS + "inclusionCriteria");
	public static final Resource incomplete = ResourceFactory.createResource(NS + "incomplete");
	public static final Resource index = ResourceFactory.createResource(NS + "index");
	public static final Resource indication = ResourceFactory.createResource(NS + "indication");
	public static final Resource indicationGuideline = ResourceFactory.createResource(NS + "indicationGuideline");
	public static final Resource inforce = ResourceFactory.createResource(NS + "inforce");
	public static final Resource information = ResourceFactory.createResource(NS + "information");
	public static final Resource informationProvider = ResourceFactory.createResource(NS + "informationProvider");
	public static final Resource informationSequence = ResourceFactory.createResource(NS + "informationSequence");
	public static final Resource informationSource = ResourceFactory.createResource(NS + "informationSource");
	public static final Resource informationType = ResourceFactory.createResource(NS + "informationType");
	public static final Resource ingredient = ResourceFactory.createResource(NS + "ingredient");
	public static final Resource ingredientLabel = ResourceFactory.createResource(NS + "ingredientLabel");
	public static final Resource initial = ResourceFactory.createResource(NS + "initial");
	public static final Resource initialFill = ResourceFactory.createResource(NS + "initialFill");
	public static final Resource initialSelected = ResourceFactory.createResource(NS + "initialSelected");
	public static final Resource initiator = ResourceFactory.createResource(NS + "initiator");
	public static final Resource initiatorActive = ResourceFactory.createResource(NS + "initiatorActive");
	public static final Resource input = ResourceFactory.createResource(NS + "input");
	public static final Resource inputParameters = ResourceFactory.createResource(NS + "inputParameters");
	public static final Resource inputPopulationId = ResourceFactory.createResource(NS + "inputPopulationId");
	public static final Resource inputProfile = ResourceFactory.createResource(NS + "inputProfile");
	public static final Resource installDate = ResourceFactory.createResource(NS + "installDate");
	public static final Resource instance = ResourceFactory.createResource(NS + "instance");
	public static final Resource instanceReference = ResourceFactory.createResource(NS + "instanceReference");
	public static final Resource instances = ResourceFactory.createResource(NS + "instances");
	public static final Resource instant = ResourceFactory.createResource(NS + "instant");
	public static final Resource instantiates = ResourceFactory.createResource(NS + "instantiates");
	public static final Resource instantiatesCanonical = ResourceFactory.createResource(NS + "instantiatesCanonical");
	public static final Resource instantiatesUri = ResourceFactory.createResource(NS + "instantiatesUri");
	public static final Resource instruction = ResourceFactory.createResource(NS + "instruction");
	public static final Resource insurance = ResourceFactory.createResource(NS + "insurance");
	public static final Resource insurancePlan = ResourceFactory.createResource(NS + "insurancePlan");
	public static final Resource insurer = ResourceFactory.createResource(NS + "insurer");
	public static final Resource integer = ResourceFactory.createResource(NS + "integer");
	public static final Resource integer64 = ResourceFactory.createResource(NS + "integer64");
	public static final Resource intended = ResourceFactory.createResource(NS + "intended");
	public static final Resource intendedEffect = ResourceFactory.createResource(NS + "intendedEffect");
	public static final Resource intendedExposure = ResourceFactory.createResource(NS + "intendedExposure");
	public static final Resource intendedJurisdiction = ResourceFactory.createResource(NS + "intendedJurisdiction");
	public static final Resource intendedRoute = ResourceFactory.createResource(NS + "intendedRoute");
	public static final Resource intendedUse = ResourceFactory.createResource(NS + "intendedUse");
	public static final Resource intent = ResourceFactory.createResource(NS + "intent");
	public static final Resource interactant = ResourceFactory.createResource(NS + "interactant");
	public static final Resource interaction = ResourceFactory.createResource(NS + "interaction");
	public static final Resource interpretation = ResourceFactory.createResource(NS + "interpretation");
	public static final Resource interpreter = ResourceFactory.createResource(NS + "interpreter");
	public static final Resource interval = ResourceFactory.createResource(NS + "interval");
	public static final Resource intervalUnit = ResourceFactory.createResource(NS + "intervalUnit");
	public static final Resource intraspecificDescription = ResourceFactory
			.createResource(NS + "intraspecificDescription");
	public static final Resource intraspecificType = ResourceFactory.createResource(NS + "intraspecificType");
	public static final Resource inventoryListing = ResourceFactory.createResource(NS + "inventoryListing");
	public static final Resource inventoryStatus = ResourceFactory.createResource(NS + "inventoryStatus");
	public static final Resource isActive = ResourceFactory.createResource(NS + "isActive");
	public static final Resource isDefault = ResourceFactory.createResource(NS + "isDefault");
	public static final Resource isDefining = ResourceFactory.createResource(NS + "isDefining");
	public static final Resource isDerived = ResourceFactory.createResource(NS + "isDerived");
	public static final Resource isExample = ResourceFactory.createResource(NS + "isExample");
	public static final Resource isModifier = ResourceFactory.createResource(NS + "isModifier");
	public static final Resource isModifierReason = ResourceFactory.createResource(NS + "isModifierReason");
	public static final Resource isSubPotent = ResourceFactory.createResource(NS + "isSubPotent");
	public static final Resource isSubpotent = ResourceFactory.createResource(NS + "isSubpotent");
	public static final Resource isSummary = ResourceFactory.createResource(NS + "isSummary");
	public static final Resource issue = ResourceFactory.createResource(NS + "issue");
	public static final Resource issued = ResourceFactory.createResource(NS + "issued");
	public static final Resource issuer = ResourceFactory.createResource(NS + "issuer");
	public static final Resource issuerType = ResourceFactory.createResource(NS + "issuerType");
	public static final Resource issues = ResourceFactory.createResource(NS + "issues");
	public static final Resource item = ResourceFactory.createResource(NS + "item");
	public static final Resource itemSequence = ResourceFactory.createResource(NS + "itemSequence");
	public static final Resource itemStatus = ResourceFactory.createResource(NS + "itemStatus");
	public static final Resource jurisdiction = ResourceFactory.createResource(NS + "jurisdiction");
	public static final Resource justification = ResourceFactory.createResource(NS + "justification");
	public static final Resource key = ResourceFactory.createResource(NS + "key");
	public static final Resource keyword = ResourceFactory.createResource(NS + "keyword");
	public static final Resource kind = ResourceFactory.createResource(NS + "kind");
	public static final Resource kingdom = ResourceFactory.createResource(NS + "kingdom");
	public static final Resource knownAllergen = ResourceFactory.createResource(NS + "knownAllergen");
	public static final Resource knownDataCount = ResourceFactory.createResource(NS + "knownDataCount");
	public static final Resource label = ResourceFactory.createResource(NS + "label");
	public static final Resource landmarkDescription = ResourceFactory.createResource(NS + "landmarkDescription");
	public static final Resource language = ResourceFactory.createResource(NS + "language");
	public static final Resource languageCode = ResourceFactory.createResource(NS + "languageCode");
	public static final Resource lastModified = ResourceFactory.createResource(NS + "lastModified");
	public static final Resource lastOccurrence = ResourceFactory.createResource(NS + "lastOccurrence");
	public static final Resource lastOccurrenceDate = ResourceFactory.createResource(NS + "lastOccurrenceDate");
	public static final Resource lastPage = ResourceFactory.createResource(NS + "lastPage");
	public static final Resource lastPerformed = ResourceFactory.createResource(NS + "lastPerformed");
	public static final Resource lastReviewDate = ResourceFactory.createResource(NS + "lastReviewDate");
	public static final Resource lastRevisionDate = ResourceFactory.createResource(NS + "lastRevisionDate");
	public static final Resource lastUpdated = ResourceFactory.createResource(NS + "lastUpdated");
	public static final Resource laterality = ResourceFactory.createResource(NS + "laterality");
	public static final Resource latitude = ResourceFactory.createResource(NS + "latitude");
	public static final Resource legal = ResourceFactory.createResource(NS + "legal");
	public static final Resource legalState = ResourceFactory.createResource(NS + "legalState");
	public static final Resource legalStatusOfSupply = ResourceFactory.createResource(NS + "legalStatusOfSupply");
	public static final Resource legallyBinding = ResourceFactory.createResource(NS + "legallyBinding");
	public static final Resource length = ResourceFactory.createResource(NS + "length");
	public static final Resource lensSpecification = ResourceFactory.createResource(NS + "lensSpecification");
	public static final Resource level = ResourceFactory.createResource(NS + "level");
	public static final Resource library = ResourceFactory.createResource(NS + "library");
	public static final Resource license = ResourceFactory.createResource(NS + "license");
	public static final Resource lifecycleStatus = ResourceFactory.createResource(NS + "lifecycleStatus");
	public static final Resource limit = ResourceFactory.createResource(NS + "limit");
	public static final Resource line = ResourceFactory.createResource(NS + "line");
	public static final Resource lineItem = ResourceFactory.createResource(NS + "lineItem");
	public static final Resource link = ResourceFactory.createResource(NS + "link");
	public static final Resource linkId = ResourceFactory.createResource(NS + "linkId");
	public static final Resource linkage = ResourceFactory.createResource(NS + "linkage");
	public static final Resource listMode = ResourceFactory.createResource(NS + "listMode");
	public static final Resource listRuleId = ResourceFactory.createResource(NS + "listRuleId");
	public static final Resource literal = ResourceFactory.createResource(NS + "literal");
	public static final Resource location = ResourceFactory.createResource(NS + "location");
	public static final Resource lockedDate = ResourceFactory.createResource(NS + "lockedDate");
	public static final Resource logMessage = ResourceFactory.createResource(NS + "logMessage");
	public static final Resource longitude = ResourceFactory.createResource(NS + "longitude");
	public static final Resource lotNumber = ResourceFactory.createResource(NS + "lotNumber");
	public static final Resource low = ResourceFactory.createResource(NS + "low");
	public static final Resource lowNumerator = ResourceFactory.createResource(NS + "lowNumerator");
	public static final Resource lowerLimit = ResourceFactory.createResource(NS + "lowerLimit");
	public static final Resource management = ResourceFactory.createResource(NS + "management");
	public static final Resource manager = ResourceFactory.createResource(NS + "manager");
	public static final Resource managingEntity = ResourceFactory.createResource(NS + "managingEntity");
	public static final Resource managingOrganization = ResourceFactory.createResource(NS + "managingOrganization");
	public static final Resource manifest = ResourceFactory.createResource(NS + "manifest");
	public static final Resource manifestation = ResourceFactory.createResource(NS + "manifestation");
	public static final Resource manipulated = ResourceFactory.createResource(NS + "manipulated");
	public static final Resource manufactureDate = ResourceFactory.createResource(NS + "manufactureDate");
	public static final Resource manufacturedDoseForm = ResourceFactory.createResource(NS + "manufacturedDoseForm");
	public static final Resource manufacturer = ResourceFactory.createResource(NS + "manufacturer");
	public static final Resource map = ResourceFactory.createResource(NS + "map");
	public static final Resource mapping = ResourceFactory.createResource(NS + "mapping");
	public static final Resource maritalStatus = ResourceFactory.createResource(NS + "maritalStatus");
	public static final Resource markdown = ResourceFactory.createResource(NS + "markdown");
	public static final Resource marketDistribution = ResourceFactory.createResource(NS + "marketDistribution");
	public static final Resource marketPeriod = ResourceFactory.createResource(NS + "marketPeriod");
	public static final Resource marketingAuthorizationHolder = ResourceFactory
			.createResource(NS + "marketingAuthorizationHolder");
	public static final Resource marketingStatus = ResourceFactory.createResource(NS + "marketingStatus");
	public static final Resource masterFile = ResourceFactory.createResource(NS + "masterFile");
	public static final Resource matchStatus = ResourceFactory.createResource(NS + "matchStatus");
	public static final Resource material = ResourceFactory.createResource(NS + "material");
	public static final Resource materialType = ResourceFactory.createResource(NS + "materialType");
	public static final Resource maternalOrganismId = ResourceFactory.createResource(NS + "maternalOrganismId");
	public static final Resource maternalOrganismName = ResourceFactory.createResource(NS + "maternalOrganismName");
	public static final Resource max = ResourceFactory.createResource(NS + "max");
	public static final Resource maxCount = ResourceFactory.createResource(NS + "maxCount");
	public static final Resource maxDispense = ResourceFactory.createResource(NS + "maxDispense");
	public static final Resource maxDosePerAdministration = ResourceFactory
			.createResource(NS + "maxDosePerAdministration");
	public static final Resource maxDosePerDay = ResourceFactory.createResource(NS + "maxDosePerDay");
	public static final Resource maxDosePerLifetime = ResourceFactory.createResource(NS + "maxDosePerLifetime");
	public static final Resource maxDosePerPeriod = ResourceFactory.createResource(NS + "maxDosePerPeriod");
	public static final Resource maxDosePerTreatmentPeriod = ResourceFactory
			.createResource(NS + "maxDosePerTreatmentPeriod");
	public static final Resource maxDuration = ResourceFactory.createResource(NS + "maxDuration");
	public static final Resource maxLength = ResourceFactory.createResource(NS + "maxLength");
	public static final Resource maxParticipants = ResourceFactory.createResource(NS + "maxParticipants");
	public static final Resource maxSingleDose = ResourceFactory.createResource(NS + "maxSingleDose");
	public static final Resource maxTreatmentPeriod = ResourceFactory.createResource(NS + "maxTreatmentPeriod");
	public static final Resource maxValue = ResourceFactory.createResource(NS + "maxValue");
	public static final Resource maxVolumeToDeliver = ResourceFactory.createResource(NS + "maxVolumeToDeliver");
	public static final Resource meaning = ResourceFactory.createResource(NS + "meaning");
	public static final Resource meaningWhenMissing = ResourceFactory.createResource(NS + "meaningWhenMissing");
	public static final Resource measure = ResourceFactory.createResource(NS + "measure");
	public static final Resource measureScore = ResourceFactory.createResource(NS + "measureScore");
	public static final Resource measurementFrequency = ResourceFactory.createResource(NS + "measurementFrequency");
	public static final Resource measurementPoint = ResourceFactory.createResource(NS + "measurementPoint");
	public static final Resource measurementType = ResourceFactory.createResource(NS + "measurementType");
	public static final Resource media = ResourceFactory.createResource(NS + "media");
	public static final Resource medication = ResourceFactory.createResource(NS + "medication");
	public static final Resource medicineClassification = ResourceFactory.createResource(NS + "medicineClassification");
	public static final Resource medium = ResourceFactory.createResource(NS + "medium");
	public static final Resource member = ResourceFactory.createResource(NS + "member");
	public static final Resource membership = ResourceFactory.createResource(NS + "membership");
	public static final Resource message = ResourceFactory.createResource(NS + "message");
	public static final Resource messaging = ResourceFactory.createResource(NS + "messaging");
	public static final Resource meta = ResourceFactory.createResource(NS + "meta");
	public static final Resource metadata = ResourceFactory.createResource(NS + "metadata");
	public static final Resource method = ResourceFactory.createResource(NS + "method");
	public static final Resource methodType = ResourceFactory.createResource(NS + "methodType");
	public static final Resource milestone = ResourceFactory.createResource(NS + "milestone");
	public static final Resource mimeType = ResourceFactory.createResource(NS + "mimeType");
	public static final Resource min = ResourceFactory.createResource(NS + "min");
	public static final Resource minValue = ResourceFactory.createResource(NS + "minValue");
	public static final Resource minimumId = ResourceFactory.createResource(NS + "minimumId");
	public static final Resource minimumVolume = ResourceFactory.createResource(NS + "minimumVolume");
	public static final Resource minutesDuration = ResourceFactory.createResource(NS + "minutesDuration");
	public static final Resource mitigatingAction = ResourceFactory.createResource(NS + "mitigatingAction");
	public static final Resource mitigation = ResourceFactory.createResource(NS + "mitigation");
	public static final Resource modality = ResourceFactory.createResource(NS + "modality");
	public static final Resource mode = ResourceFactory.createResource(NS + "mode");
	public static final Resource modelCharacteristic = ResourceFactory.createResource(NS + "modelCharacteristic");
	public static final Resource modelNumber = ResourceFactory.createResource(NS + "modelNumber");
	public static final Resource modification = ResourceFactory.createResource(NS + "modification");
	public static final Resource modifier = ResourceFactory.createResource(NS + "modifier");
	public static final Resource modifierExtension = ResourceFactory.createResource(NS + "modifierExtension");
	public static final Resource modifierExtensionClass = ResourceFactory.createResource(NS + "modifierExtensionClass");
	public static final Resource modifierExtensionProperty = ResourceFactory
			.createResource(NS + "modifierExtensionProperty");
	public static final Resource module = ResourceFactory.createResource(NS + "module");
	public static final Resource moiety = ResourceFactory.createResource(NS + "moiety");
	public static final Resource molecularFormula = ResourceFactory.createResource(NS + "molecularFormula");
	public static final Resource molecularFormulaByMoiety = ResourceFactory
			.createResource(NS + "molecularFormulaByMoiety");
	public static final Resource molecularWeight = ResourceFactory.createResource(NS + "molecularWeight");
	public static final Resource monday = ResourceFactory.createResource(NS + "monday");
	public static final Resource monitoringProgram = ResourceFactory.createResource(NS + "monitoringProgram");
	public static final Resource monograph = ResourceFactory.createResource(NS + "monograph");
	public static final Resource monomerSet = ResourceFactory.createResource(NS + "monomerSet");
	public static final Resource monthInterval = ResourceFactory.createResource(NS + "monthInterval");
	public static final Resource monthlyTemplate = ResourceFactory.createResource(NS + "monthlyTemplate");
	public static final Resource morphology = ResourceFactory.createResource(NS + "morphology");
	public static final Resource multipleAnd = ResourceFactory.createResource(NS + "multipleAnd");
	public static final Resource multipleBirth = ResourceFactory.createResource(NS + "multipleBirth");
	public static final Resource multipleOr = ResourceFactory.createResource(NS + "multipleOr");
	public static final Resource multipleResultsAllowed = ResourceFactory.createResource(NS + "multipleResultsAllowed");
	public static final Resource mustHaveValue = ResourceFactory.createResource(NS + "mustHaveValue");
	public static final Resource mustSupport = ResourceFactory.createResource(NS + "mustSupport");
	public static final Resource nTerminalModification = ResourceFactory.createResource(NS + "nTerminalModification");
	public static final Resource nTerminalModificationId = ResourceFactory
			.createResource(NS + "nTerminalModificationId");
	public static final Resource name = ResourceFactory.createResource(NS + "name");
	public static final Resource nameType = ResourceFactory.createResource(NS + "nameType");
	public static final Resource narrative = ResourceFactory.createResource(NS + "narrative");
	public static final Resource navigationLinks = ResourceFactory.createResource(NS + "navigationLinks");
	public static final Resource need = ResourceFactory.createResource(NS + "need");
	public static final Resource needsMap = ResourceFactory.createResource(NS + "needsMap");
	public static final Resource net = ResourceFactory.createResource(NS + "net");
	public static final Resource netContent = ResourceFactory.createResource(NS + "netContent");
	public static final Resource network = ResourceFactory.createResource(NS + "network");
	public static final Resource next = ResourceFactory.createResource(NS + "next");
	public static final Resource nextScheduled = ResourceFactory.createResource(NS + "nextScheduled");
	public static final Resource noMap = ResourceFactory.createResource(NS + "noMap");
	public static final Resource node = ResourceFactory.createResource(NS + "node");
	public static final Resource nodeId = ResourceFactory.createResource(NS + "nodeId");
	public static final Resource nodeRole = ResourceFactory.createResource(NS + "nodeRole");
	public static final Resource normalCodedValueSet = ResourceFactory.createResource(NS + "normalCodedValueSet");
	public static final Resource normalValue = ResourceFactory.createResource(NS + "normalValue");
	public static final Resource notAvailableTime = ResourceFactory.createResource(NS + "notAvailableTime");
	public static final Resource notConsumed = ResourceFactory.createResource(NS + "notConsumed");
	public static final Resource notConsumedReason = ResourceFactory.createResource(NS + "notConsumedReason");
	public static final Resource notPerformedReason = ResourceFactory.createResource(NS + "notPerformedReason");
	public static final Resource note = ResourceFactory.createResource(NS + "note");
	public static final Resource noteNumber = ResourceFactory.createResource(NS + "noteNumber");
	public static final Resource notificationEvent = ResourceFactory.createResource(NS + "notificationEvent");
	public static final Resource notificationShape = ResourceFactory.createResource(NS + "notificationShape");
	public static final Resource nthWeekOfMonth = ResourceFactory.createResource(NS + "nthWeekOfMonth");
	public static final Resource nucleicAcid = ResourceFactory.createResource(NS + "nucleicAcid");
	public static final Resource number = ResourceFactory.createResource(NS + "number");
	public static final Resource numberAffected = ResourceFactory.createResource(NS + "numberAffected");
	public static final Resource numberOfEvents = ResourceFactory.createResource(NS + "numberOfEvents");
	public static final Resource numberOfInstances = ResourceFactory.createResource(NS + "numberOfInstances");
	public static final Resource numberOfParticipants = ResourceFactory.createResource(NS + "numberOfParticipants");
	public static final Resource numberOfRepeatsAllowed = ResourceFactory.createResource(NS + "numberOfRepeatsAllowed");
	public static final Resource numberOfSeries = ResourceFactory.createResource(NS + "numberOfSeries");
	public static final Resource numberOfStudies = ResourceFactory.createResource(NS + "numberOfStudies");
	public static final Resource numberOfSubunits = ResourceFactory.createResource(NS + "numberOfSubunits");
	public static final Resource numerator = ResourceFactory.createResource(NS + "numerator");
	public static final Resource nutrient = ResourceFactory.createResource(NS + "nutrient");
	public static final Resource nutritionProduct = ResourceFactory.createResource(NS + "nutritionProduct");
	public static final Resource object = ResourceFactory.createResource(NS + "object");
	public static final Resource objective = ResourceFactory.createResource(NS + "objective");
	public static final Resource observation = ResourceFactory.createResource(NS + "observation");
	public static final Resource observationRequirement = ResourceFactory.createResource(NS + "observationRequirement");
	public static final Resource observationResultRequirement = ResourceFactory
			.createResource(NS + "observationResultRequirement");
	public static final Resource observed = ResourceFactory.createResource(NS + "observed");
	public static final Resource observedGroup = ResourceFactory.createResource(NS + "observedGroup");
	public static final Resource observer = ResourceFactory.createResource(NS + "observer");
	public static final Resource occurence = ResourceFactory.createResource(NS + "occurence");
	public static final Resource occurred = ResourceFactory.createResource(NS + "occurred");
	public static final Resource occurrence = ResourceFactory.createResource(NS + "occurrence");
	public static final Resource occurrenceChanged = ResourceFactory.createResource(NS + "occurrenceChanged");
	public static final Resource occurrenceCount = ResourceFactory.createResource(NS + "occurrenceCount");
	public static final Resource occurrenceDate = ResourceFactory.createResource(NS + "occurrenceDate");
	public static final Resource occurrenceDateTime = ResourceFactory.createResource(NS + "occurrenceDateTime");
	public static final Resource offer = ResourceFactory.createResource(NS + "offer");
	public static final Resource offeredIn = ResourceFactory.createResource(NS + "offeredIn");
	public static final Resource official = ResourceFactory.createResource(NS + "official");
	public static final Resource offset = ResourceFactory.createResource(NS + "offset");
	public static final Resource offsets = ResourceFactory.createResource(NS + "offsets");
	public static final Resource oid = ResourceFactory.createResource(NS + "oid");
	public static final Resource oligoNucleotideType = ResourceFactory.createResource(NS + "oligoNucleotideType");
	public static final Resource onAdmission = ResourceFactory.createResource(NS + "onAdmission");
	public static final Resource onBehalfOf = ResourceFactory.createResource(NS + "onBehalfOf");
	public static final Resource onHold = ResourceFactory.createResource(NS + "onHold");
	public static final Resource onset = ResourceFactory.createResource(NS + "onset");
	public static final Resource op = ResourceFactory.createResource(NS + "op");
	public static final Resource operation = ResourceFactory.createResource(NS + "operation");
	public static final Resource operationType = ResourceFactory.createResource(NS + "operationType");
	public static final Resource operationTypeReason = ResourceFactory.createResource(NS + "operationTypeReason");
	public static final Resource operationalStatus = ResourceFactory.createResource(NS + "operationalStatus");
	public static final Resource operator = ResourceFactory.createResource(NS + "operator");
	public static final Resource opticalActivity = ResourceFactory.createResource(NS + "opticalActivity");
	public static final Resource option = ResourceFactory.createResource(NS + "option");
	public static final Resource oralDiet = ResourceFactory.createResource(NS + "oralDiet");
	public static final Resource order = ResourceFactory.createResource(NS + "order");
	public static final Resource orderDetail = ResourceFactory.createResource(NS + "orderDetail");
	public static final Resource orderMeaning = ResourceFactory.createResource(NS + "orderMeaning");
	public static final Resource ordered = ResourceFactory.createResource(NS + "ordered");
	public static final Resource orderedBy = ResourceFactory.createResource(NS + "orderedBy");
	public static final Resource orderer = ResourceFactory.createResource(NS + "orderer");
	public static final Resource ordinalPosition = ResourceFactory.createResource(NS + "ordinalPosition");
	public static final Resource organism = ResourceFactory.createResource(NS + "organism");
	public static final Resource organismGeneral = ResourceFactory.createResource(NS + "organismGeneral");
	public static final Resource organismId = ResourceFactory.createResource(NS + "organismId");
	public static final Resource organismName = ResourceFactory.createResource(NS + "organismName");
	public static final Resource organismType = ResourceFactory.createResource(NS + "organismType");
	public static final Resource organization = ResourceFactory.createResource(NS + "organization");
	public static final Resource organizationReference = ResourceFactory.createResource(NS + "organizationReference");
	public static final Resource orientation = ResourceFactory.createResource(NS + "orientation");
	public static final Resource origin = ResourceFactory.createResource(NS + "origin");
	public static final Resource originRelationshipType = ResourceFactory.createResource(NS + "originRelationshipType");
	public static final Resource originalPrescription = ResourceFactory.createResource(NS + "originalPrescription");
	public static final Resource originatingAppointment = ResourceFactory.createResource(NS + "originatingAppointment");
	public static final Resource other = ResourceFactory.createResource(NS + "other");
	public static final Resource otherMap = ResourceFactory.createResource(NS + "otherMap");
	public static final Resource otherTherapy = ResourceFactory.createResource(NS + "otherTherapy");
	public static final Resource outcome = ResourceFactory.createResource(NS + "outcome");
	public static final Resource outcomeMeasure = ResourceFactory.createResource(NS + "outcomeMeasure");
	public static final Resource output = ResourceFactory.createResource(NS + "output");
	public static final Resource outputParameters = ResourceFactory.createResource(NS + "outputParameters");
	public static final Resource outputProfile = ResourceFactory.createResource(NS + "outputProfile");
	public static final Resource outsideFoodAllowed = ResourceFactory.createResource(NS + "outsideFoodAllowed");
	public static final Resource overbooked = ResourceFactory.createResource(NS + "overbooked");
	public static final Resource overload = ResourceFactory.createResource(NS + "overload");
	public static final Resource overrideReason = ResourceFactory.createResource(NS + "overrideReason");
	public static final Resource ownedBy = ResourceFactory.createResource(NS + "ownedBy");
	public static final Resource owner = ResourceFactory.createResource(NS + "owner");
	public static final Resource packageCode = ResourceFactory.createResource(NS + "packageCode");
	public static final Resource packageFor = ResourceFactory.createResource(NS + "packageFor");
	public static final Resource packageId = ResourceFactory.createResource(NS + "packageId");
	public static final Resource packagedMedicinalProduct = ResourceFactory
			.createResource(NS + "packagedMedicinalProduct");
	public static final Resource packagedProduct = ResourceFactory.createResource(NS + "packagedProduct");
	public static final Resource packaging = ResourceFactory.createResource(NS + "packaging");
	public static final Resource page = ResourceFactory.createResource(NS + "page");
	public static final Resource pageCount = ResourceFactory.createResource(NS + "pageCount");
	public static final Resource pageString = ResourceFactory.createResource(NS + "pageString");
	public static final Resource pages = ResourceFactory.createResource(NS + "pages");
	public static final Resource paging = ResourceFactory.createResource(NS + "paging");
	public static final Resource param = ResourceFactory.createResource(NS + "param");
	public static final Resource parameter = ResourceFactory.createResource(NS + "parameter");
	public static final Resource parameterFocus = ResourceFactory.createResource(NS + "parameterFocus");
	public static final Resource parameterName = ResourceFactory.createResource(NS + "parameterName");
	public static final Resource params = ResourceFactory.createResource(NS + "params");
	public static final Resource parent = ResourceFactory.createResource(NS + "parent");
	public static final Resource parentSubstanceId = ResourceFactory.createResource(NS + "parentSubstanceId");
	public static final Resource parentSubstanceName = ResourceFactory.createResource(NS + "parentSubstanceName");
	public static final Resource part = ResourceFactory.createResource(NS + "part");
	public static final Resource partDescription = ResourceFactory.createResource(NS + "partDescription");
	public static final Resource partLocation = ResourceFactory.createResource(NS + "partLocation");
	public static final Resource partNumber = ResourceFactory.createResource(NS + "partNumber");
	public static final Resource partOf = ResourceFactory.createResource(NS + "partOf");
	public static final Resource participant = ResourceFactory.createResource(NS + "participant");
	public static final Resource participantStatus = ResourceFactory.createResource(NS + "participantStatus");
	public static final Resource participantType = ResourceFactory.createResource(NS + "participantType");
	public static final Resource participatingOrganization = ResourceFactory
			.createResource(NS + "participatingOrganization");
	public static final Resource party = ResourceFactory.createResource(NS + "party");
	public static final Resource patchFormat = ResourceFactory.createResource(NS + "patchFormat");
	public static final Resource paternalOrganismId = ResourceFactory.createResource(NS + "paternalOrganismId");
	public static final Resource paternalOrganismName = ResourceFactory.createResource(NS + "paternalOrganismName");
	public static final Resource path = ResourceFactory.createResource(NS + "path");
	public static final Resource patient = ResourceFactory.createResource(NS + "patient");
	public static final Resource patientCharacteristic = ResourceFactory.createResource(NS + "patientCharacteristic");
	public static final Resource patientInstruction = ResourceFactory.createResource(NS + "patientInstruction");
	public static final Resource patientPaid = ResourceFactory.createResource(NS + "patientPaid");
	public static final Resource patientPreparation = ResourceFactory.createResource(NS + "patientPreparation");
	public static final Resource pattern = ResourceFactory.createResource(NS + "pattern");
	public static final Resource pause = ResourceFactory.createResource(NS + "pause");
	public static final Resource payee = ResourceFactory.createResource(NS + "payee");
	public static final Resource payeeType = ResourceFactory.createResource(NS + "payeeType");
	public static final Resource payload = ResourceFactory.createResource(NS + "payload");
	public static final Resource payment = ResourceFactory.createResource(NS + "payment");
	public static final Resource paymentBy = ResourceFactory.createResource(NS + "paymentBy");
	public static final Resource paymentDate = ResourceFactory.createResource(NS + "paymentDate");
	public static final Resource paymentIdentifier = ResourceFactory.createResource(NS + "paymentIdentifier");
	public static final Resource paymentIssuer = ResourceFactory.createResource(NS + "paymentIssuer");
	public static final Resource paymentStatus = ResourceFactory.createResource(NS + "paymentStatus");
	public static final Resource paymentTerms = ResourceFactory.createResource(NS + "paymentTerms");
	public static final Resource pediatricUseIndicator = ResourceFactory.createResource(NS + "pediatricUseIndicator");
	public static final Resource performed = ResourceFactory.createResource(NS + "performed");
	public static final Resource performedActivity = ResourceFactory.createResource(NS + "performedActivity");
	public static final Resource performer = ResourceFactory.createResource(NS + "performer");
	public static final Resource performerLinkId = ResourceFactory.createResource(NS + "performerLinkId");
	public static final Resource performerRole = ResourceFactory.createResource(NS + "performerRole");
	public static final Resource performerType = ResourceFactory.createResource(NS + "performerType");
	public static final Resource performingOrganization = ResourceFactory.createResource(NS + "performingOrganization");
	public static final Resource period = ResourceFactory.createResource(NS + "period");
	public static final Resource periodMax = ResourceFactory.createResource(NS + "periodMax");
	public static final Resource periodType = ResourceFactory.createResource(NS + "periodType");
	public static final Resource periodUnit = ResourceFactory.createResource(NS + "periodUnit");
	public static final Resource permittedDataType = ResourceFactory.createResource(NS + "permittedDataType");
	public static final Resource permittedUnit = ResourceFactory.createResource(NS + "permittedUnit");
	public static final Resource phase = ResourceFactory.createResource(NS + "phase");
	public static final Resource photo = ResourceFactory.createResource(NS + "photo");
	public static final Resource phylum = ResourceFactory.createResource(NS + "phylum");
	public static final Resource plan = ResourceFactory.createResource(NS + "plan");
	public static final Resource plannedActivityReference = ResourceFactory
			.createResource(NS + "plannedActivityReference");
	public static final Resource plannedEndDate = ResourceFactory.createResource(NS + "plannedEndDate");
	public static final Resource plannedStartDate = ResourceFactory.createResource(NS + "plannedStartDate");
	public static final Resource planningHorizon = ResourceFactory.createResource(NS + "planningHorizon");
	public static final Resource points = ResourceFactory.createResource(NS + "points");
	public static final Resource policy = ResourceFactory.createResource(NS + "policy");
	public static final Resource policyBasis = ResourceFactory.createResource(NS + "policyBasis");
	public static final Resource policyHolder = ResourceFactory.createResource(NS + "policyHolder");
	public static final Resource policyText = ResourceFactory.createResource(NS + "policyText");
	public static final Resource polymer = ResourceFactory.createResource(NS + "polymer");
	public static final Resource population = ResourceFactory.createResource(NS + "population");
	public static final Resource position = ResourceFactory.createResource(NS + "position");
	public static final Resource positiveInt = ResourceFactory.createResource(NS + "positiveInt");
	public static final Resource postConditions = ResourceFactory.createResource(NS + "postConditions");
	public static final Resource postalCode = ResourceFactory.createResource(NS + "postalCode");
	public static final Resource power = ResourceFactory.createResource(NS + "power");
	public static final Resource practiceSetting = ResourceFactory.createResource(NS + "practiceSetting");
	public static final Resource practitioner = ResourceFactory.createResource(NS + "practitioner");
	public static final Resource preAdmissionIdentifier = ResourceFactory.createResource(NS + "preAdmissionIdentifier");
	public static final Resource preAuthPeriod = ResourceFactory.createResource(NS + "preAuthPeriod");
	public static final Resource preAuthRef = ResourceFactory.createResource(NS + "preAuthRef");
	public static final Resource preAuthRefPeriod = ResourceFactory.createResource(NS + "preAuthRefPeriod");
	public static final Resource preConditions = ResourceFactory.createResource(NS + "preConditions");
	public static final Resource precedence = ResourceFactory.createResource(NS + "precedence");
	public static final Resource precheckBehavior = ResourceFactory.createResource(NS + "precheckBehavior");
	public static final Resource precondition = ResourceFactory.createResource(NS + "precondition");
	public static final Resource predecessor = ResourceFactory.createResource(NS + "predecessor");
	public static final Resource prediction = ResourceFactory.createResource(NS + "prediction");
	public static final Resource preference = ResourceFactory.createResource(NS + "preference");
	public static final Resource preferred = ResourceFactory.createResource(NS + "preferred");
	public static final Resource preferredReportName = ResourceFactory.createResource(NS + "preferredReportName");
	public static final Resource prefix = ResourceFactory.createResource(NS + "prefix");
	public static final Resource preparation = ResourceFactory.createResource(NS + "preparation");
	public static final Resource preparationInstruction = ResourceFactory.createResource(NS + "preparationInstruction");
	public static final Resource preparedDate = ResourceFactory.createResource(NS + "preparedDate");
	public static final Resource prescriber = ResourceFactory.createResource(NS + "prescriber");
	public static final Resource prescription = ResourceFactory.createResource(NS + "prescription");
	public static final Resource presentation = ResourceFactory.createResource(NS + "presentation");
	public static final Resource presentedForm = ResourceFactory.createResource(NS + "presentedForm");
	public static final Resource preventiveAction = ResourceFactory.createResource(NS + "preventiveAction");
	public static final Resource previous = ResourceFactory.createResource(NS + "previous");
	public static final Resource previousAppointment = ResourceFactory.createResource(NS + "previousAppointment");
	public static final Resource priceComponent = ResourceFactory.createResource(NS + "priceComponent");
	public static final Resource primaryPurposeType = ResourceFactory.createResource(NS + "primaryPurposeType");
	public static final Resource primarySource = ResourceFactory.createResource(NS + "primarySource");
	public static final Resource priorPrescription = ResourceFactory.createResource(NS + "priorPrescription");
	public static final Resource priority = ResourceFactory.createResource(NS + "priority");
	public static final Resource prism = ResourceFactory.createResource(NS + "prism");
	public static final Resource probability = ResourceFactory.createResource(NS + "probability");
	public static final Resource problem = ResourceFactory.createResource(NS + "problem");
	public static final Resource procedure = ResourceFactory.createResource(NS + "procedure");
	public static final Resource procedureSequence = ResourceFactory.createResource(NS + "procedureSequence");
	public static final Resource process = ResourceFactory.createResource(NS + "process");
	public static final Resource processNote = ResourceFactory.createResource(NS + "processNote");
	public static final Resource processing = ResourceFactory.createResource(NS + "processing");
	public static final Resource processingFacility = ResourceFactory.createResource(NS + "processingFacility");
	public static final Resource processingMode = ResourceFactory.createResource(NS + "processingMode");
	public static final Resource processor = ResourceFactory.createResource(NS + "processor");
	public static final Resource producedFrom = ResourceFactory.createResource(NS + "producedFrom");
	public static final Resource product = ResourceFactory.createResource(NS + "product");
	public static final Resource productCategory = ResourceFactory.createResource(NS + "productCategory");
	public static final Resource productCode = ResourceFactory.createResource(NS + "productCode");
	public static final Resource productName = ResourceFactory.createResource(NS + "productName");
	public static final Resource productOrService = ResourceFactory.createResource(NS + "productOrService");
	public static final Resource productOrServiceEnd = ResourceFactory.createResource(NS + "productOrServiceEnd");
	public static final Resource productReference = ResourceFactory.createResource(NS + "productReference");
	public static final Resource productStatus = ResourceFactory.createResource(NS + "productStatus");
	public static final Resource productType = ResourceFactory.createResource(NS + "productType");
	public static final Resource productionIdentifierInUDI = ResourceFactory
			.createResource(NS + "productionIdentifierInUDI");
	public static final Resource profile = ResourceFactory.createResource(NS + "profile");
	public static final Resource prognosisCodeableConcept = ResourceFactory
			.createResource(NS + "prognosisCodeableConcept");
	public static final Resource prognosisReference = ResourceFactory.createResource(NS + "prognosisReference");
	public static final Resource program = ResourceFactory.createResource(NS + "program");
	public static final Resource programCode = ResourceFactory.createResource(NS + "programCode");
	public static final Resource programEligibility = ResourceFactory.createResource(NS + "programEligibility");
	public static final Resource programStatus = ResourceFactory.createResource(NS + "programStatus");
	public static final Resource progress = ResourceFactory.createResource(NS + "progress");
	public static final Resource progressStatus = ResourceFactory.createResource(NS + "progressStatus");
	public static final Resource property = ResourceFactory.createResource(NS + "property");
	public static final Resource propertyGroup = ResourceFactory.createResource(NS + "propertyGroup");
	public static final Resource proposedNewTime = ResourceFactory.createResource(NS + "proposedNewTime");
	public static final Resource protein = ResourceFactory.createResource(NS + "protein");
	public static final Resource protocol = ResourceFactory.createResource(NS + "protocol");
	public static final Resource protocolApplied = ResourceFactory.createResource(NS + "protocolApplied");
	public static final Resource protocolPerformed = ResourceFactory.createResource(NS + "protocolPerformed");
	public static final Resource providedBy = ResourceFactory.createResource(NS + "providedBy");
	public static final Resource provider = ResourceFactory.createResource(NS + "provider");
	public static final Resource provision = ResourceFactory.createResource(NS + "provision");
	public static final Resource provisionActor = ResourceFactory.createResource(NS + "provisionActor");
	public static final Resource provisionData = ResourceFactory.createResource(NS + "provisionData");
	public static final Resource proxyIdentityCertificate = ResourceFactory
			.createResource(NS + "proxyIdentityCertificate");
	public static final Resource proxySignature = ResourceFactory.createResource(NS + "proxySignature");
	public static final Resource publicationDate = ResourceFactory.createResource(NS + "publicationDate");
	public static final Resource publicationDateSeason = ResourceFactory.createResource(NS + "publicationDateSeason");
	public static final Resource publicationDateText = ResourceFactory.createResource(NS + "publicationDateText");
	public static final Resource publicationForm = ResourceFactory.createResource(NS + "publicationForm");
	public static final Resource publicationStatus = ResourceFactory.createResource(NS + "publicationStatus");
	public static final Resource publishedIn = ResourceFactory.createResource(NS + "publishedIn");
	public static final Resource publisher = ResourceFactory.createResource(NS + "publisher");
	public static final Resource publisherLocation = ResourceFactory.createResource(NS + "publisherLocation");
	public static final Resource purpose = ResourceFactory.createResource(NS + "purpose");
	public static final Resource pushTypeAvailable = ResourceFactory.createResource(NS + "pushTypeAvailable");
	public static final Resource qualification = ResourceFactory.createResource(NS + "qualification");
	public static final Resource qualifiedValue = ResourceFactory.createResource(NS + "qualifiedValue");
	public static final Resource qualifier = ResourceFactory.createResource(NS + "qualifier");
	public static final Resource qualifiers = ResourceFactory.createResource(NS + "qualifiers");
	public static final Resource qualitativeRisk = ResourceFactory.createResource(NS + "qualitativeRisk");
	public static final Resource quantity = ResourceFactory.createResource(NS + "quantity");
	public static final Resource query = ResourceFactory.createResource(NS + "query");
	public static final Resource queryCriteria = ResourceFactory.createResource(NS + "queryCriteria");
	public static final Resource question = ResourceFactory.createResource(NS + "question");
	public static final Resource questionnaire = ResourceFactory.createResource(NS + "questionnaire");
	public static final Resource range = ResourceFactory.createResource(NS + "Range");
	public static final Resource rangeCategory = ResourceFactory.createResource(NS + "RangeCategory");
	public static final Resource rank = ResourceFactory.createResource(NS + "Rank");
	public static final Resource rankingOrder = ResourceFactory.createResource(NS + "RankingOrder");
	public static final Resource rate = ResourceFactory.createResource(NS + "Rate");
	public static final Resource rateAggregation = ResourceFactory.createResource(NS + "RateAggregation");
	public static final Resource rater = ResourceFactory.createResource(NS + "Rater");
	public static final Resource rating = ResourceFactory.createResource(NS + "Rating");
	public static final Resource ratioHighLimitAmount = ResourceFactory.createResource(NS + "RatioHighLimitAmount");
	public static final Resource ratioType = ResourceFactory.createResource(NS + "RatioType");
	public static final Resource rationale = ResourceFactory.createResource(NS + "Rationale");
	public static final Resource reAdmission = ResourceFactory.createResource(NS + "ReAdmission");
	public static final Resource reaction = ResourceFactory.createResource(NS + "Reaction");
	public static final Resource readHistory = ResourceFactory.createResource(NS + "ReadHistory");
	public static final Resource readOnly = ResourceFactory.createResource(NS + "ReadOnly");
	public static final Resource reason = ResourceFactory.createResource(NS + "Reason");
	public static final Resource reasonLinkId = ResourceFactory.createResource(NS + "ReasonLinkId");
	public static final Resource recall = ResourceFactory.createResource(NS + "Recall");
	public static final Resource received = ResourceFactory.createResource(NS + "Received");
	public static final Resource receivedTime = ResourceFactory.createResource(NS + "ReceivedTime");
	public static final Resource receiver = ResourceFactory.createResource(NS + "Receiver");
	public static final Resource receiverActive = ResourceFactory.createResource(NS + "ReceiverActive");
	public static final Resource recipient = ResourceFactory.createResource(NS + "Recipient");
	public static final Resource recommendation = ResourceFactory.createResource(NS + "Recommendation");
	public static final Resource recorded = ResourceFactory.createResource(NS + "Recorded");
	public static final Resource recordedDate = ResourceFactory.createResource(NS + "RecordedDate");
	public static final Resource recorder = ResourceFactory.createResource(NS + "Recorder");
	public static final Resource recruitment = ResourceFactory.createResource(NS + "Recruitment");
	public static final Resource recurrenceId = ResourceFactory.createResource(NS + "RecurrenceId");
	public static final Resource recurrenceTemplate = ResourceFactory.createResource(NS + "RecurrenceTemplate");
	public static final Resource recurrenceType = ResourceFactory.createResource(NS + "RecurrenceType");
	public static final Resource recurring = ResourceFactory.createResource(NS + "Recurring");
	public static final Resource reference = ResourceFactory.createResource(NS + "Reference");
	public static final Resource referenceInformation = ResourceFactory.createResource(NS + "ReferenceInformation");
	public static final Resource referenceNumber = ResourceFactory.createResource(NS + "ReferenceNumber");
	public static final Resource referencePolicy = ResourceFactory.createResource(NS + "ReferencePolicy");
	public static final Resource referenceRange = ResourceFactory.createResource(NS + "ReferenceRange");
	public static final Resource referenceStrength = ResourceFactory.createResource(NS + "ReferenceStrength");
	public static final Resource referencedFrom = ResourceFactory.createResource(NS + "ReferencedFrom");
	public static final Resource referral = ResourceFactory.createResource(NS + "Referral");
	public static final Resource referralMethod = ResourceFactory.createResource(NS + "ReferralMethod");
	public static final Resource referralRequest = ResourceFactory.createResource(NS + "ReferralRequest");
	public static final Resource referrer = ResourceFactory.createResource(NS + "Referrer");
	public static final Resource region = ResourceFactory.createResource(NS + "Region");
	public static final Resource regionType = ResourceFactory.createResource(NS + "RegionType");
	public static final Resource regionsCalled = ResourceFactory.createResource(NS + "RegionsCalled");
	public static final Resource regionsStudied = ResourceFactory.createResource(NS + "RegionsStudied");
	public static final Resource regulator = ResourceFactory.createResource(NS + "Regulator");
	public static final Resource regulatory = ResourceFactory.createResource(NS + "Regulatory");
	public static final Resource regulatoryAuthority = ResourceFactory.createResource(NS + "RegulatoryAuthority");
	public static final Resource regulatoryBasis = ResourceFactory.createResource(NS + "RegulatoryBasis");
	public static final Resource regulatoryIdentifier = ResourceFactory.createResource(NS + "RegulatoryIdentifier");
	public static final Resource rejectionCriterion = ResourceFactory.createResource(NS + "RejectionCriterion");
	public static final Resource related = ResourceFactory.createResource(NS + "Related");
	public static final Resource relatedAccount = ResourceFactory.createResource(NS + "RelatedAccount");
	public static final Resource relatedAction = ResourceFactory.createResource(NS + "RelatedAction");
	public static final Resource relatedArtifact = ResourceFactory.createResource(NS + "RelatedArtifact");
	public static final Resource relatedClinicalInformation = ResourceFactory
			.createResource(NS + "RelatedClinicalInformation");
	public static final Resource relatedData = ResourceFactory.createResource(NS + "RelatedData");
	public static final Resource relatedDevice = ResourceFactory.createResource(NS + "RelatedDevice");
	public static final Resource relatedIdentifier = ResourceFactory.createResource(NS + "RelatedIdentifier");
	public static final Resource relatedItem = ResourceFactory.createResource(NS + "RelatedItem");
	public static final Resource relatedMedicationKnowledge = ResourceFactory
			.createResource(NS + "RelatedMedicationKnowledge");
	public static final Resource relatesTo = ResourceFactory.createResource(NS + "RelatesTo");
	public static final Resource relation = ResourceFactory.createResource(NS + "Relation");
	public static final Resource relationship = ResourceFactory.createResource(NS + "Relationship");
	public static final Resource relationshipType = ResourceFactory.createResource(NS + "RelationshipType");
	public static final Resource relative = ResourceFactory.createResource(NS + "Relative");
	public static final Resource relativePath = ResourceFactory.createResource(NS + "RelativePath");
	public static final Resource relativeRisk = ResourceFactory.createResource(NS + "RelativeRisk");
	public static final Resource releaseDate = ResourceFactory.createResource(NS + "ReleaseDate");
	public static final Resource relevantHistory = ResourceFactory.createResource(NS + "RelevantHistory");
	public static final Resource reliableCache = ResourceFactory.createResource(NS + "ReliableCache");
	public static final Resource renderedDosageInstruction = ResourceFactory
			.createResource(NS + "RenderedDosageInstruction");
	public static final Resource rendering = ResourceFactory.createResource(NS + "Rendering");
	public static final Resource repeat = ResourceFactory.createResource(NS + "Repeat");
	public static final Resource repeatUnit = ResourceFactory.createResource(NS + "RepeatUnit");
	public static final Resource repeatUnitAmountType = ResourceFactory.createResource(NS + "RepeatUnitAmountType");
	public static final Resource repeats = ResourceFactory.createResource(NS + "Repeats");
	public static final Resource repetitions = ResourceFactory.createResource(NS + "Repetitions");
	public static final Resource replacedSequence = ResourceFactory.createResource(NS + "ReplacedSequence");
	public static final Resource replacementSequence = ResourceFactory.createResource(NS + "ReplacementSequence");
	public static final Resource replaces = ResourceFactory.createResource(NS + "Replaces");
	public static final Resource report = ResourceFactory.createResource(NS + "Report");
	public static final Resource reported = ResourceFactory.createResource(NS + "Reported");
	public static final Resource reportedDateTime = ResourceFactory.createResource(NS + "ReportedDateTime");
	public static final Resource reporter = ResourceFactory.createResource(NS + "Reporter");
	public static final Resource reportingPeriod = ResourceFactory.createResource(NS + "ReportingPeriod");
	public static final Resource reportingVendor = ResourceFactory.createResource(NS + "ReportingVendor");
	public static final Resource representation = ResourceFactory.createResource(NS + "Representation");
	public static final Resource request = ResourceFactory.createResource(NS + "Request");
	public static final Resource requestHeader = ResourceFactory.createResource(NS + "RequestHeader");
	public static final Resource requestId = ResourceFactory.createResource(NS + "RequestId");
	public static final Resource requestIdentifier = ResourceFactory.createResource(NS + "RequestIdentifier");
	public static final Resource requestMethod = ResourceFactory.createResource(NS + "RequestMethod");
	public static final Resource requestProvider = ResourceFactory.createResource(NS + "RequestProvider");
	public static final Resource requestURL = ResourceFactory.createResource(NS + "RequestURL");
	public static final Resource requestedLocation = ResourceFactory.createResource(NS + "RequestedLocation");
	public static final Resource requestedPerformer = ResourceFactory.createResource(NS + "RequestedPerformer");
	public static final Resource requestedPeriod = ResourceFactory.createResource(NS + "RequestedPeriod");
	public static final Resource requester = ResourceFactory.createResource(NS + "Requester");
	public static final Resource requesterLinkId = ResourceFactory.createResource(NS + "RequesterLinkId");
	public static final Resource requestingOrganization = ResourceFactory.createResource(NS + "RequestingOrganization");
	public static final Resource requestor = ResourceFactory.createResource(NS + "Requestor");
	public static final Resource requireBoth = ResourceFactory.createResource(NS + "RequireBoth");
	public static final Resource required = ResourceFactory.createResource(NS + "Required");
	public static final Resource requiredBehavior = ResourceFactory.createResource(NS + "RequiredBehavior");
	public static final Resource requirement = ResourceFactory.createResource(NS + "Requirement");
	public static final Resource requirements = ResourceFactory.createResource(NS + "Requirements");
	public static final Resource requisition = ResourceFactory.createResource(NS + "Requisition");
	public static final Resource residueSite = ResourceFactory.createResource(NS + "ResidueSite");
	public static final Resource resourceReference = ResourceFactory.createResource(NS + "ResourceReference");
	public static final Resource resourceTrigger = ResourceFactory.createResource(NS + "ResourceTrigger");
	public static final Resource resourceType = ResourceFactory.createResource(NS + "ResourceType");
	public static final Resource response = ResourceFactory.createResource(NS + "Response");
	public static final Resource responseCode = ResourceFactory.createResource(NS + "ResponseCode");
	public static final Resource responseId = ResourceFactory.createResource(NS + "ResponseId");
	public static final Resource responseRequired = ResourceFactory.createResource(NS + "ResponseRequired");
	public static final Resource responsibility = ResourceFactory.createResource(NS + "Responsibility");
	public static final Resource responsible = ResourceFactory.createResource(NS + "Responsible");
	public static final Resource responsibleOrganization = ResourceFactory
			.createResource(NS + "ResponsibleOrganization");
	public static final Resource responsibleParty = ResourceFactory.createResource(NS + "ResponsibleParty");
	public static final Resource rest = ResourceFactory.createResource(NS + "Rest");
	public static final Resource restoreDate = ResourceFactory.createResource(NS + "RestoreDate");
	public static final Resource restriction = ResourceFactory.createResource(NS + "Restriction");
	public static final Resource result = ResourceFactory.createResource(NS + "Result");
	public static final Resource resultForCreate = ResourceFactory.createResource(NS + "ResultForCreate");
	public static final Resource resultForDelete = ResourceFactory.createResource(NS + "ResultForDelete");
	public static final Resource resultingEffect = ResourceFactory.createResource(NS + "ResultingEffect");
	public static final Resource resultsInterpreter = ResourceFactory.createResource(NS + "ResultsInterpreter");
	public static final Resource retentionTime = ResourceFactory.createResource(NS + "RetentionTime");
	public static final Resource returnedAmount = ResourceFactory.createResource(NS + "ReturnedAmount");
	public static final Resource revInclude = ResourceFactory.createResource(NS + "RevInclude");
	public static final Resource revenue = ResourceFactory.createResource(NS + "Revenue");
	public static final Resource reviewOutcome = ResourceFactory.createResource(NS + "ReviewOutcome");
	public static final Resource reviewer = ResourceFactory.createResource(NS + "Reviewer");
	public static final Resource riskAdjustment = ResourceFactory.createResource(NS + "RiskAdjustment");
	public static final Resource role = ResourceFactory.createResource(NS + "Role");
	public static final Resource route = ResourceFactory.createResource(NS + "Route");
	public static final Resource routeOfAdministration = ResourceFactory.createResource(NS + "RouteOfAdministration");
	public static final Resource rule = ResourceFactory.createResource(NS + "Rule");
	public static final Resource rules = ResourceFactory.createResource(NS + "Rules");
	public static final Resource safety = ResourceFactory.createResource(NS + "safety");
	public static final Resource sampleSize = ResourceFactory.createResource(NS + "sampleSize");
	public static final Resource satisfiedBy = ResourceFactory.createResource(NS + "satisfiedBy");
	public static final Resource saturday = ResourceFactory.createResource(NS + "saturday");
	public static final Resource schedule = ResourceFactory.createResource(NS + "schedule");
	public static final Resource scope = ResourceFactory.createResource(NS + "scope");
	public static final Resource score = ResourceFactory.createResource(NS + "score");
	public static final Resource scoring = ResourceFactory.createResource(NS + "scoring");
	public static final Resource scoringUnit = ResourceFactory.createResource(NS + "scoringUnit");
	public static final Resource script = ResourceFactory.createResource(NS + "script");
	public static final Resource search = ResourceFactory.createResource(NS + "search");
	public static final Resource searchInclude = ResourceFactory.createResource(NS + "searchInclude");
	public static final Resource searchParam = ResourceFactory.createResource(NS + "searchParam");
	public static final Resource searchRevInclude = ResourceFactory.createResource(NS + "searchRevInclude");
	public static final Resource searchType = ResourceFactory.createResource(NS + "searchType");
	public static final Resource section = ResourceFactory.createResource(NS + "section");
	public static final Resource security = ResourceFactory.createResource(NS + "security");
	public static final Resource securityContext = ResourceFactory.createResource(NS + "securityContext");
	public static final Resource securityLabel = ResourceFactory.createResource(NS + "securityLabel");
	public static final Resource securityLabelNumber = ResourceFactory.createResource(NS + "securityLabelNumber");
	public static final Resource selectionBehavior = ResourceFactory.createResource(NS + "selectionBehavior");
	public static final Resource sender = ResourceFactory.createResource(NS + "sender");
	public static final Resource sent = ResourceFactory.createResource(NS + "sent");
	public static final Resource sequence = ResourceFactory.createResource(NS + "sequence");
	public static final Resource sequenceAttachment = ResourceFactory.createResource(NS + "sequenceAttachment");
	public static final Resource sequenceRange = ResourceFactory.createResource(NS + "sequenceRange");
	public static final Resource sequenceType = ResourceFactory.createResource(NS + "sequenceType");
	public static final Resource serialNumber = ResourceFactory.createResource(NS + "serialNumber");
	public static final Resource series = ResourceFactory.createResource(NS + "series");
	public static final Resource seriesDoses = ResourceFactory.createResource(NS + "seriesDoses");
	public static final Resource seriesNumber = ResourceFactory.createResource(NS + "seriesNumber");
	public static final Resource seriesUid = ResourceFactory.createResource(NS + "seriesUid");
	public static final Resource seriousness = ResourceFactory.createResource(NS + "seriousness");
	public static final Resource service = ResourceFactory.createResource(NS + "service");
	public static final Resource serviceCategory = ResourceFactory.createResource(NS + "serviceCategory");
	public static final Resource servicePeriod = ResourceFactory.createResource(NS + "servicePeriod");
	public static final Resource serviceProvider = ResourceFactory.createResource(NS + "serviceProvider");
	public static final Resource serviceProvisionCode = ResourceFactory.createResource(NS + "serviceProvisionCode");
	public static final Resource serviceType = ResourceFactory.createResource(NS + "serviceType");
	public static final Resource serviced = ResourceFactory.createResource(NS + "serviced");
	public static final Resource sessionKey = ResourceFactory.createResource(NS + "sessionKey");
	public static final Resource setup = ResourceFactory.createResource(NS + "setup");
	public static final Resource severity = ResourceFactory.createResource(NS + "severity");
	public static final Resource sex = ResourceFactory.createResource(NS + "sex");
	public static final Resource shelfLifeStorage = ResourceFactory.createResource(NS + "shelfLifeStorage");
	public static final Resource fhir_short = ResourceFactory.createResource(NS + "short");
	public static final Resource shortDoco = ResourceFactory.createResource(NS + "shortDoco");
	public static final Resource shortTitle = ResourceFactory.createResource(NS + "shortTitle");
	public static final Resource sigFormat = ResourceFactory.createResource(NS + "sigFormat");
	public static final Resource signature = ResourceFactory.createResource(NS + "signature");
	public static final Resource signer = ResourceFactory.createResource(NS + "signer");
	public static final Resource singleUse = ResourceFactory.createResource(NS + "singleUse");
	public static final Resource site = ResourceFactory.createResource(NS + "site");
	public static final Resource situation = ResourceFactory.createResource(NS + "situation");
	public static final Resource size = ResourceFactory.createResource(NS + "size");
	public static final Resource sliceIsConstraining = ResourceFactory.createResource(NS + "sliceIsConstraining");
	public static final Resource sliceName = ResourceFactory.createResource(NS + "sliceName");
	public static final Resource slicing = ResourceFactory.createResource(NS + "slicing");
	public static final Resource slot = ResourceFactory.createResource(NS + "slot");
	public static final Resource snapshot = ResourceFactory.createResource(NS + "snapshot");
	public static final Resource software = ResourceFactory.createResource(NS + "software");
	public static final Resource sopClass = ResourceFactory.createResource(NS + "sopClass");
	public static final Resource sort = ResourceFactory.createResource(NS + "sort");
	public static final Resource source = ResourceFactory.createResource(NS + "source");
	public static final Resource sourceAttachment = ResourceFactory.createResource(NS + "sourceAttachment");
	public static final Resource sourceDocument = ResourceFactory.createResource(NS + "sourceDocument");
	public static final Resource sourceId = ResourceFactory.createResource(NS + "sourceId");
	public static final Resource sourceIdentityCertificate = ResourceFactory
			.createResource(NS + "sourceIdentityCertificate");
	public static final Resource sourceMaterial = ResourceFactory.createResource(NS + "sourceMaterial");
	public static final Resource sourceMaterialClass = ResourceFactory.createResource(NS + "sourceMaterialClass");
	public static final Resource sourceMaterialState = ResourceFactory.createResource(NS + "sourceMaterialState");
	public static final Resource sourceMaterialType = ResourceFactory.createResource(NS + "sourceMaterialType");
	public static final Resource sourceReference = ResourceFactory.createResource(NS + "sourceReference");
	public static final Resource sourceScope = ResourceFactory.createResource(NS + "sourceScope");
	public static final Resource sourceSignature = ResourceFactory.createResource(NS + "sourceSignature");
	public static final Resource spatialReference = ResourceFactory.createResource(NS + "spatialReference");
	public static final Resource specialArrangement = ResourceFactory.createResource(NS + "specialArrangement");
	public static final Resource specialCourtesy = ResourceFactory.createResource(NS + "specialCourtesy");
	public static final Resource specialMeasures = ResourceFactory.createResource(NS + "specialMeasures");
	public static final Resource specialPrecautionsForStorage = ResourceFactory
			.createResource(NS + "specialPrecautionsForStorage");
	public static final Resource specialty = ResourceFactory.createResource(NS + "specialty");
	public static final Resource species = ResourceFactory.createResource(NS + "species");
	public static final Resource specificCost = ResourceFactory.createResource(NS + "specificCost");
	public static final Resource specification = ResourceFactory.createResource(NS + "specification");
	public static final Resource specimen = ResourceFactory.createResource(NS + "specimen");
	public static final Resource specimenQuantity = ResourceFactory.createResource(NS + "specimenQuantity");
	public static final Resource specimenRequirement = ResourceFactory.createResource(NS + "specimenRequirement");
	public static final Resource sphere = ResourceFactory.createResource(NS + "sphere");
	public static final Resource stabilityDuration = ResourceFactory.createResource(NS + "stabilityDuration");
	public static final Resource stage = ResourceFactory.createResource(NS + "stage");
	public static final Resource start = ResourceFactory.createResource(NS + "start");
	public static final Resource startDate = ResourceFactory.createResource(NS + "startDate");
	public static final Resource startParam = ResourceFactory.createResource(NS + "startParam");
	public static final Resource started = ResourceFactory.createResource(NS + "started");
	public static final Resource startingMaterial = ResourceFactory.createResource(NS + "startingMaterial");
	public static final Resource startingSequence = ResourceFactory.createResource(NS + "startingSequence");
	public static final Resource state = ResourceFactory.createResource(NS + "state");
	public static final Resource statement = ResourceFactory.createResource(NS + "statement");
	public static final Resource statistic = ResourceFactory.createResource(NS + "statistic");
	public static final Resource statisticType = ResourceFactory.createResource(NS + "statisticType");
	public static final Resource status = ResourceFactory.createResource(NS + "status");
	public static final Resource statusChanged = ResourceFactory.createResource(NS + "statusChanged");
	public static final Resource statusDate = ResourceFactory.createResource(NS + "statusDate");
	public static final Resource statusHistory = ResourceFactory.createResource(NS + "statusHistory");
	public static final Resource statusReason = ResourceFactory.createResource(NS + "statusReason");
	public static final Resource step = ResourceFactory.createResource(NS + "step");
	public static final Resource stereochemistry = ResourceFactory.createResource(NS + "stereochemistry");
	public static final Resource stopTestOnFail = ResourceFactory.createResource(NS + "stopTestOnFail");
	public static final Resource storageGuideline = ResourceFactory.createResource(NS + "storageGuideline");
	public static final Resource storageTempRequirements = ResourceFactory
			.createResource(NS + "storageTempRequirements");
	public static final Resource strand = ResourceFactory.createResource(NS + "strand");
	public static final Resource stratifier = ResourceFactory.createResource(NS + "stratifier");
	public static final Resource stratum = ResourceFactory.createResource(NS + "stratum");
	public static final Resource strength = ResourceFactory.createResource(NS + "strength");
	public static final Resource string = ResourceFactory.createResource(NS + "string");
	public static final Resource structuralRepresentation = ResourceFactory
			.createResource(NS + "structuralRepresentation");
	public static final Resource structure = ResourceFactory.createResource(NS + "structure");
	public static final Resource structureProfile = ResourceFactory.createResource(NS + "structureProfile");
	public static final Resource structureType = ResourceFactory.createResource(NS + "structureType");
	public static final Resource structureVersion = ResourceFactory.createResource(NS + "structureVersion");
	public static final Resource study = ResourceFactory.createResource(NS + "study");
	public static final Resource studyDesign = ResourceFactory.createResource(NS + "studyDesign");
	public static final Resource studyUid = ResourceFactory.createResource(NS + "studyUid");
	public static final Resource style = ResourceFactory.createResource(NS + "style");
	public static final Resource subDetail = ResourceFactory.createResource(NS + "subDetail");
	public static final Resource subDetailSequence = ResourceFactory.createResource(NS + "subDetailSequence");
	public static final Resource subJurisdiction = ResourceFactory.createResource(NS + "subJurisdiction");
	public static final Resource subPotentReason = ResourceFactory.createResource(NS + "subPotentReason");
	public static final Resource subProperty = ResourceFactory.createResource(NS + "subProperty");
	public static final Resource subSite = ResourceFactory.createResource(NS + "subSite");
	public static final Resource subType = ResourceFactory.createResource(NS + "subType");
	public static final Resource subcomponent = ResourceFactory.createResource(NS + "subcomponent");
	public static final Resource subdetailSequence = ResourceFactory.createResource(NS + "subdetailSequence");
	public static final Resource subject = ResourceFactory.createResource(NS + "subject");
	public static final Resource subjectReport = ResourceFactory.createResource(NS + "subjectReport");
	public static final Resource subjectResults = ResourceFactory.createResource(NS + "subjectResults");
	public static final Resource subjectState = ResourceFactory.createResource(NS + "subjectState");
	public static final Resource subjectStatus = ResourceFactory.createResource(NS + "subjectStatus");
	public static final Resource subjectType = ResourceFactory.createResource(NS + "subjectType");
	public static final Resource subjects = ResourceFactory.createResource(NS + "subjects");
	public static final Resource submitter = ResourceFactory.createResource(NS + "submitter");
	public static final Resource subpotentReason = ResourceFactory.createResource(NS + "subpotentReason");
	public static final Resource subrogation = ResourceFactory.createResource(NS + "subrogation");
	public static final Resource subscriber = ResourceFactory.createResource(NS + "subscriber");
	public static final Resource subscriberId = ResourceFactory.createResource(NS + "subscriberId");
	public static final Resource subscription = ResourceFactory.createResource(NS + "subscription");
	public static final Resource subscriptionTopic = ResourceFactory.createResource(NS + "subscriptionTopic");
	public static final Resource subset = ResourceFactory.createResource(NS + "subset");
	public static final Resource substance = ResourceFactory.createResource(NS + "substance");
	public static final Resource substanceDefinition = ResourceFactory.createResource(NS + "substanceDefinition");
	public static final Resource substitution = ResourceFactory.createResource(NS + "substitution");
	public static final Resource subsumption = ResourceFactory.createResource(NS + "subsumption");
	public static final Resource subtitle = ResourceFactory.createResource(NS + "subtitle");
	public static final Resource subtype = ResourceFactory.createResource(NS + "subtype");
	public static final Resource subunit = ResourceFactory.createResource(NS + "subunit");
	public static final Resource suffix = ResourceFactory.createResource(NS + "suffix");
	public static final Resource sugar = ResourceFactory.createResource(NS + "sugar");
	public static final Resource summary = ResourceFactory.createResource(NS + "summary");
	public static final Resource sunday = ResourceFactory.createResource(NS + "sunday");
	public static final Resource supplement = ResourceFactory.createResource(NS + "supplement");
	public static final Resource supplementalData = ResourceFactory.createResource(NS + "supplementalData");
	public static final Resource supplements = ResourceFactory.createResource(NS + "supplements");
	public static final Resource suppliedItem = ResourceFactory.createResource(NS + "suppliedItem");
	public static final Resource supplier = ResourceFactory.createResource(NS + "supplier");
	public static final Resource supportedInteraction = ResourceFactory.createResource(NS + "supportedInteraction");
	public static final Resource supportedMessage = ResourceFactory.createResource(NS + "supportedMessage");
	public static final Resource supportedProfile = ResourceFactory.createResource(NS + "supportedProfile");
	public static final Resource supportingImmunization = ResourceFactory.createResource(NS + "supportingImmunization");
	public static final Resource supportingInfo = ResourceFactory.createResource(NS + "supportingInfo");
	public static final Resource supportingInfoSequence = ResourceFactory.createResource(NS + "supportingInfoSequence");
	public static final Resource supportingInformation = ResourceFactory.createResource(NS + "supportingInformation");
	public static final Resource supportingPatientInformation = ResourceFactory
			.createResource(NS + "supportingPatientInformation");
	public static final Resource suppress = ResourceFactory.createResource(NS + "suppress");
	public static final Resource surfaceOrientation = ResourceFactory.createResource(NS + "surfaceOrientation");
	public static final Resource suspectEntity = ResourceFactory.createResource(NS + "suspectEntity");
	public static final Resource symptomConditionEffect = ResourceFactory.createResource(NS + "symptomConditionEffect");
	public static final Resource synonym = ResourceFactory.createResource(NS + "synonym");
	public static final Resource synthesisType = ResourceFactory.createResource(NS + "synthesisType");
	public static final Resource system = ResourceFactory.createResource(NS + "system");
	public static final Resource tag = ResourceFactory.createResource(NS + "tag");
	public static final Resource target = ResourceFactory.createResource(NS + "target");
	public static final Resource targetDisease = ResourceFactory.createResource(NS + "targetDisease");
	public static final Resource targetFormat = ResourceFactory.createResource(NS + "targetFormat");
	public static final Resource targetId = ResourceFactory.createResource(NS + "targetId");
	public static final Resource targetItem = ResourceFactory.createResource(NS + "targetItem");
	public static final Resource targetLocation = ResourceFactory.createResource(NS + "targetLocation");
	public static final Resource targetNumber = ResourceFactory.createResource(NS + "targetNumber");
	public static final Resource targetProfile = ResourceFactory.createResource(NS + "targetProfile");
	public static final Resource targetScope = ResourceFactory.createResource(NS + "targetScope");
	public static final Resource targetSpecies = ResourceFactory.createResource(NS + "targetSpecies");
	public static final Resource tax = ResourceFactory.createResource(NS + "tax");
	public static final Resource team = ResourceFactory.createResource(NS + "team");
	public static final Resource teardown = ResourceFactory.createResource(NS + "teardown");
	public static final Resource technique = ResourceFactory.createResource(NS + "technique");
	public static final Resource telecom = ResourceFactory.createResource(NS + "telecom");
	public static final Resource temperatureQualifier = ResourceFactory.createResource(NS + "temperatureQualifier");
	public static final Resource temperatureRange = ResourceFactory.createResource(NS + "temperatureRange");
	public static final Resource template = ResourceFactory.createResource(NS + "template");
	public static final Resource tenderedAmount = ResourceFactory.createResource(NS + "tenderedAmount");
	public static final Resource term = ResourceFactory.createResource(NS + "term");
	public static final Resource test = ResourceFactory.createResource(NS + "test");
	public static final Resource testCase = ResourceFactory.createResource(NS + "testCase");
	public static final Resource testData = ResourceFactory.createResource(NS + "testData");
	public static final Resource testRun = ResourceFactory.createResource(NS + "testRun");
	public static final Resource testScript = ResourceFactory.createResource(NS + "testScript");
	public static final Resource testTools = ResourceFactory.createResource(NS + "testTools");
	public static final Resource tester = ResourceFactory.createResource(NS + "tester");
	public static final Resource testingDestination = ResourceFactory.createResource(NS + "testingDestination");
	public static final Resource text = ResourceFactory.createResource(NS + "text");
	public static final Resource textConcentration = ResourceFactory.createResource(NS + "textConcentration");
	public static final Resource textEquivalent = ResourceFactory.createResource(NS + "textEquivalent");
	public static final Resource textFilter = ResourceFactory.createResource(NS + "textFilter");
	public static final Resource textPresentation = ResourceFactory.createResource(NS + "textPresentation");
	public static final Resource texture = ResourceFactory.createResource(NS + "texture");
	public static final Resource threePrime = ResourceFactory.createResource(NS + "threePrime");
	public static final Resource threshold = ResourceFactory.createResource(NS + "threshold");
	public static final Resource thursday = ResourceFactory.createResource(NS + "thursday");
	public static final Resource time = ResourceFactory.createResource(NS + "time");
	public static final Resource timeAspect = ResourceFactory.createResource(NS + "timeAspect");
	public static final Resource timeFromEvent = ResourceFactory.createResource(NS + "timeFromEvent");
	public static final Resource timeOfDay = ResourceFactory.createResource(NS + "timeOfDay");
	public static final Resource timeout = ResourceFactory.createResource(NS + "timeout");
	public static final Resource timestamp = ResourceFactory.createResource(NS + "timestamp");
	public static final Resource timezone = ResourceFactory.createResource(NS + "timezone");
	public static final Resource timing = ResourceFactory.createResource(NS + "timing");
	public static final Resource tissue = ResourceFactory.createResource(NS + "tissue");
	public static final Resource title = ResourceFactory.createResource(NS + "title");
	public static final Resource topic = ResourceFactory.createResource(NS + "topic");
	public static final Resource total = ResourceFactory.createResource(NS + "total");
	public static final Resource totalGross = ResourceFactory.createResource(NS + "totalGross");
	public static final Resource totalNet = ResourceFactory.createResource(NS + "totalNet");
	public static final Resource totalPriceComponent = ResourceFactory.createResource(NS + "totalPriceComponent");
	public static final Resource totalVolume = ResourceFactory.createResource(NS + "totalVolume");
	public static final Resource traceNumber = ResourceFactory.createResource(NS + "traceNumber");
	public static final Resource transform = ResourceFactory.createResource(NS + "transform");
	public static final Resource translation = ResourceFactory.createResource(NS + "translation");
	public static final Resource translations = ResourceFactory.createResource(NS + "translations");
	public static final Resource treatment = ResourceFactory.createResource(NS + "treatment");
	public static final Resource treatmentIntent = ResourceFactory.createResource(NS + "treatmentIntent");
	public static final Resource treeRoot = ResourceFactory.createResource(NS + "treeRoot");
	public static final Resource trigger = ResourceFactory.createResource(NS + "trigger");
	public static final Resource triggeredBy = ResourceFactory.createResource(NS + "triggeredBy");
	public static final Resource tuesday = ResourceFactory.createResource(NS + "tuesday");
	public static final Resource type = ResourceFactory.createResource(NS + "type");
	public static final Resource typeCanonical = ResourceFactory.createResource(NS + "typeCanonical");
	public static final Resource typeCollected = ResourceFactory.createResource(NS + "typeCollected");
	public static final Resource typeMode = ResourceFactory.createResource(NS + "typeMode");
	public static final Resource typeReference = ResourceFactory.createResource(NS + "typeReference");
	public static final Resource typeTested = ResourceFactory.createResource(NS + "typeTested");
	public static final Resource udi = ResourceFactory.createResource(NS + "udi");
	public static final Resource udiCarrier = ResourceFactory.createResource(NS + "udiCarrier");
	public static final Resource udiDeviceIdentifier = ResourceFactory.createResource(NS + "udiDeviceIdentifier");
	public static final Resource uid = ResourceFactory.createResource(NS + "uid");
	public static final Resource undesirableEffect = ResourceFactory.createResource(NS + "undesirableEffect");
	public static final Resource uniqueId = ResourceFactory.createResource(NS + "uniqueId");
	public static final Resource unit = ResourceFactory.createResource(NS + "unit");
	public static final Resource unitOfPresentation = ResourceFactory.createResource(NS + "unitOfPresentation");
	public static final Resource unitPrice = ResourceFactory.createResource(NS + "unitPrice");
	public static final Resource unitPriceComponent = ResourceFactory.createResource(NS + "unitPriceComponent");
	public static final Resource unmapped = ResourceFactory.createResource(NS + "unmapped");
	public static final Resource unsignedInt = ResourceFactory.createResource(NS + "unsignedInt");
	public static final Resource updateCreate = ResourceFactory.createResource(NS + "updateCreate");
	public static final Resource upperLimit = ResourceFactory.createResource(NS + "upperLimit");
	public static final Resource uri = ResourceFactory.createResource(NS + "uri");
	public static final Resource usage = ResourceFactory.createResource(NS + "usage");
	public static final Resource usageInstruction = ResourceFactory.createResource(NS + "usageInstruction");
	public static final Resource usageReason = ResourceFactory.createResource(NS + "usageReason");
	public static final Resource usageStatus = ResourceFactory.createResource(NS + "usageStatus");
	public static final Resource use = ResourceFactory.createResource(NS + "use");
	public static final Resource useBy = ResourceFactory.createResource(NS + "useBy");
	public static final Resource useContext = ResourceFactory.createResource(NS + "useContext");
	public static final Resource usePeriod = ResourceFactory.createResource(NS + "usePeriod");
	public static final Resource used = ResourceFactory.createResource(NS + "used");
	public static final Resource userSelected = ResourceFactory.createResource(NS + "userSelected");
	public static final Resource uuid = ResourceFactory.createResource(NS + "uuid");
	public static final Resource v = ResourceFactory.createResource(NS + "v");
	public static final Resource vaccineCode = ResourceFactory.createResource(NS + "vaccineCode");
	public static final Resource validCodedValueSet = ResourceFactory.createResource(NS + "validCodedValueSet");
	public static final Resource validateCode = ResourceFactory.createResource(NS + "validateCode");
	public static final Resource validateProfileId = ResourceFactory.createResource(NS + "validateProfileId");
	public static final Resource validated = ResourceFactory.createResource(NS + "validated");
	public static final Resource validationDate = ResourceFactory.createResource(NS + "validationDate");
	public static final Resource validationProcess = ResourceFactory.createResource(NS + "validationProcess");
	public static final Resource validationStatus = ResourceFactory.createResource(NS + "validationStatus");
	public static final Resource validationType = ResourceFactory.createResource(NS + "validationType");
	public static final Resource validator = ResourceFactory.createResource(NS + "validator");
	public static final Resource validity = ResourceFactory.createResource(NS + "validity");
	public static final Resource validityPeriod = ResourceFactory.createResource(NS + "validityPeriod");
	public static final Resource value = ResourceFactory.createResource(NS + "value");
	public static final Resource valueAlternatives = ResourceFactory.createResource(NS + "valueAlternatives");
	public static final Resource valueCategory = ResourceFactory.createResource(NS + "valueCategory");
	public static final Resource valueFilter = ResourceFactory.createResource(NS + "valueFilter");
	public static final Resource valueQuantity = ResourceFactory.createResource(NS + "valueQuantity");
	public static final Resource valueRange = ResourceFactory.createResource(NS + "valueRange");
	public static final Resource valueSet = ResourceFactory.createResource(NS + "valueSet");
	public static final Resource valuedItem = ResourceFactory.createResource(NS + "valuedItem");
	public static final Resource variable = ResourceFactory.createResource(NS + "variable");
	public static final Resource variableDefinition = ResourceFactory.createResource(NS + "variableDefinition");
	public static final Resource variableRole = ResourceFactory.createResource(NS + "variableRole");
	public static final Resource verification = ResourceFactory.createResource(NS + "verification");
	public static final Resource verificationDate = ResourceFactory.createResource(NS + "verificationDate");
	public static final Resource verificationStatus = ResourceFactory.createResource(NS + "verificationStatus");
	public static final Resource verificationType = ResourceFactory.createResource(NS + "verificationType");
	public static final Resource verified = ResourceFactory.createResource(NS + "verified");
	public static final Resource verifiedBy = ResourceFactory.createResource(NS + "verifiedBy");
	public static final Resource verifiedWith = ResourceFactory.createResource(NS + "verifiedWith");
	public static final Resource version = ResourceFactory.createResource(NS + "version");
	public static final Resource versionAlgorithm = ResourceFactory.createResource(NS + "versionAlgorithm");
	public static final Resource versionId = ResourceFactory.createResource(NS + "versionId");
	public static final Resource versionNeeded = ResourceFactory.createResource(NS + "versionNeeded");
	public static final Resource versionReference = ResourceFactory.createResource(NS + "versionReference");
	public static final Resource versioning = ResourceFactory.createResource(NS + "versioning");
	public static final Resource virtualService = ResourceFactory.createResource(NS + "virtualService");
	public static final Resource volume = ResourceFactory.createResource(NS + "volume");
	public static final Resource warning = ResourceFactory.createResource(NS + "warning");
	public static final Resource warningOnly = ResourceFactory.createResource(NS + "warningOnly");
	public static final Resource wasSubstituted = ResourceFactory.createResource(NS + "wasSubstituted");
	public static final Resource webLocation = ResourceFactory.createResource(NS + "webLocation");
	public static final Resource wednesday = ResourceFactory.createResource(NS + "wednesday");
	public static final Resource weekInterval = ResourceFactory.createResource(NS + "weekInterval");
	public static final Resource weeklyTemplate = ResourceFactory.createResource(NS + "weeklyTemplate");
	public static final Resource what = ResourceFactory.createResource(NS + "what");
	public static final Resource when = ResourceFactory.createResource(NS + "when");
	public static final Resource whenHandedOver = ResourceFactory.createResource(NS + "whenHandedOver");
	public static final Resource whenPrepared = ResourceFactory.createResource(NS + "whenPrepared");
	public static final Resource who = ResourceFactory.createResource(NS + "who");
	public static final Resource whyStopped = ResourceFactory.createResource(NS + "whyStopped");
	public static final Resource width = ResourceFactory.createResource(NS + "width");
	public static final Resource windowEnd = ResourceFactory.createResource(NS + "windowEnd");
	public static final Resource windowStart = ResourceFactory.createResource(NS + "windowStart");
	public static final Resource withdrawalPeriod = ResourceFactory.createResource(NS + "withdrawalPeriod");
	public static final Resource workflow = ResourceFactory.createResource(NS + "workflow");
	public static final Resource workflowStatus = ResourceFactory.createResource(NS + "workflowStatus");
	public static final Resource xhtml = ResourceFactory.createResource(NS + "xhtml");
	public static final Resource yearInterval = ResourceFactory.createResource(NS + "yearInterval");
	public static final Resource yearlyTemplate = ResourceFactory.createResource(NS + "yearlyTemplate");

	public static final Set<Resource> allFhirResources = Arrays.stream(FhirRdf.class.getDeclaredFields())
			.filter(f -> f.getType().equals(Resource.class)).map(f -> {
				try {
					return (Resource) f.get(FhirRdf.class);
				} catch (IllegalArgumentException | IllegalAccessException e) {
					throw new IllegalStateException(e);
				}
			}).collect(Collectors.toSet());

	public static final Set<String> allFhirResourceStrings = allFhirResources.stream().map(r -> r.getURI())
			.collect(Collectors.toSet());

}
